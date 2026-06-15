#pragma once

#include <cstddef>
#include <functional>
#include <iterator>
#include <stdexcept>
#include <utility>

// ---------------------------------------------------------------------------
// RBTreeMap<K, V, Compare>
//
// Ordered key-value store backed by a Red-Black tree.
// All nodes are also threaded into a doubly-linked list in key order,
// making iterator increment/decrement O(1) without walking the tree.
//
// Interface mirrors std::map: same iterator semantics, same lookup API.
// Implementation lives in RBTreeMap.hpp (included at the bottom of this file
// once the class body is complete).
// ---------------------------------------------------------------------------

template <typename K, typename V, typename Compare = std::less<K>>
class RBTreeMap {
    // =======================================================================
    // Public type aliases  (must come first — Node uses value_type)
    // =======================================================================
public:
    using key_type        = K;
    using mapped_type     = V;
    using value_type      = std::pair<const K, V>;
    using size_type       = std::size_t;
    using difference_type = std::ptrdiff_t;
    using key_compare     = Compare;

    // =======================================================================
    // Private internals
    // =======================================================================
private:
    enum class Color : bool { Red = true, Black = false };

    struct Node {
        value_type  kv;       // pair<const K, V> — what iterators expose
        Color       color;
        Node*       parent;
        Node*       left;
        Node*       right;
        Node*       prev;     // in-order predecessor in the doubly-linked list
        Node*       next;     // in-order successor  in the doubly-linked list

        // Construct a real node.  Args are forwarded to value_type's ctor,
        // so both copy/move (from a pair) and piecewise_construct work.
        // Tree and list links are wired later by _insertNode.
        template <typename... Args>
        explicit Node(Args&&... args);

        // Construct the nil sentinel.  kv is left uninitialised — never read.
        struct NilTag {};
        explicit Node(NilTag) noexcept;
    };

    // =======================================================================
    // iterator
    // =======================================================================
public:
    struct iterator {
        using iterator_category = std::bidirectional_iterator_tag;
        using value_type        = RBTreeMap::value_type;
        using difference_type   = std::ptrdiff_t;
        using pointer           = value_type*;
        using reference         = value_type&;

        iterator() noexcept;

        reference operator*()  const noexcept;
        pointer   operator->() const noexcept;

        iterator& operator++()    noexcept;   // pre-increment  (advance to next key)
        iterator  operator++(int) noexcept;   // post-increment
        iterator& operator--()    noexcept;   // pre-decrement  (retreat to prev key)
        iterator  operator--(int) noexcept;   // post-decrement

        bool operator==(const iterator& rhs) const noexcept;
        bool operator!=(const iterator& rhs) const noexcept;

    private:
        Node*       _node;  // current node; equals _nil when this is end()
        const Node* _nil;   // sentinel — _nil->prev is the last real node,
                            // enabling --end() without touching the tree

        explicit iterator(Node* node, const Node* nil) noexcept;

        friend class RBTreeMap;
    };

    // =======================================================================
    // const_iterator
    // =======================================================================
    struct const_iterator {
        using iterator_category = std::bidirectional_iterator_tag;
        using value_type        = RBTreeMap::value_type;
        using difference_type   = std::ptrdiff_t;
        using pointer           = const value_type*;
        using reference         = const value_type&;

        const_iterator() noexcept;
        const_iterator(const iterator& it) noexcept;   // implicit: iterator → const_iterator

        reference operator*()  const noexcept;
        pointer   operator->() const noexcept;

        const_iterator& operator++()    noexcept;
        const_iterator  operator++(int) noexcept;
        const_iterator& operator--()    noexcept;
        const_iterator  operator--(int) noexcept;

        bool operator==(const const_iterator& rhs) const noexcept;
        bool operator!=(const const_iterator& rhs) const noexcept;

    private:
        const Node* _node;
        const Node* _nil;

        explicit const_iterator(const Node* node, const Node* nil) noexcept;

        friend class RBTreeMap;
    };

    // =======================================================================
    // Construction / destruction / assignment
    // =======================================================================
public:
    RBTreeMap();
    ~RBTreeMap();

    RBTreeMap(const RBTreeMap& other);
    RBTreeMap(RBTreeMap&&  other) noexcept;

    RBTreeMap& operator=(const RBTreeMap& other);
    RBTreeMap& operator=(RBTreeMap&&  other) noexcept;

    // =======================================================================
    // Capacity
    // =======================================================================
    size_type size()  const noexcept;
    bool      empty() const noexcept;

    // =======================================================================
    // Iterators
    // =======================================================================
    iterator       begin()  noexcept;
    iterator       end()    noexcept;
    const_iterator begin()  const noexcept;
    const_iterator end()    const noexcept;
    const_iterator cbegin() const noexcept;
    const_iterator cend()   const noexcept;

    // =======================================================================
    // Element access
    // =======================================================================

    // Inserts a default-constructed V if key is absent, then returns reference.
    V& operator[](const K& key);
    V& operator[](K&& key);

    // Throws std::out_of_range if key is absent.
    V&       at(const K& key);
    const V& at(const K& key) const;

    // =======================================================================
    // Modifiers
    // =======================================================================

    // Returns {iterator-to-element, true} on insertion,
    //         {iterator-to-existing, false} if the key was already present.
    std::pair<iterator, bool> insert(const value_type& value);
    std::pair<iterator, bool> insert(value_type&& value);

    // Constructs value_type in-place (piecewise_construct supported).
    template <typename... Args>
    std::pair<iterator, bool> emplace(Args&&... args);

    // Erase by iterator — returns iterator to the element that followed pos.
    iterator  erase(iterator pos);
    iterator  erase(const_iterator pos);

    // Erase by key — returns number of elements removed (0 or 1).
    size_type erase(const K& key);

    void clear() noexcept;

    // =======================================================================
    // Lookup
    // =======================================================================

    // Returns end() if key is not found.
    iterator       find(const K& key);
    const_iterator find(const K& key) const;

    // First element whose key is not less than k  (key >= k).
    // Returns end() when no such element exists.
    iterator       lower_bound(const K& key);
    const_iterator lower_bound(const K& key) const;

    // First element whose key is greater than k  (key > k).
    // Returns end() when no such element exists.
    iterator       upper_bound(const K& key);
    const_iterator upper_bound(const K& key) const;

    // Returns 0 or 1 (keys are unique).
    size_type count(const K& key) const;

    bool contains(const K& key) const;

    // =======================================================================
    // Private data
    // =======================================================================
private:
    // _nil is the shared sentinel used for both the RB tree and the linked list:
    //
    //   Tree role:
    //     • Every "null" child pointer (and root->parent) points to _nil.
    //     • _nil->color is always Black — simplifies fixup without branch guards.
    //
    //   List role:
    //     • _nil->next  →  first in-order real node  (or _nil if empty)
    //     • _nil->prev  →  last  in-order real node  (or _nil if empty)
    //     • end()   returns iterator(_nil)
    //     • begin() returns iterator(_nil->next)
    //     • --end() follows _nil->prev, reaching the last element in O(1)
    Node*     _nil;
    Node*     _root;
    size_type _size;
    Compare   _comp;

    // =======================================================================
    // Private helpers — implemented in RBTreeMap.hpp
    // =======================================================================

    // --- RB-tree rotations and fixups ---
    void _rotateLeft (Node* x) noexcept;
    void _rotateRight(Node* x) noexcept;
    void _insertFixup(Node* z) noexcept;   // restore RB invariant after insert
    void _deleteFixup(Node* x) noexcept;   // restore RB invariant after erase
    void _transplant (Node* u, Node* v) noexcept;  // splice v into u's position

    // --- Tree navigation ---
    Node* _minimum(Node* x) const noexcept;   // leftmost node in subtree
    Node* _maximum(Node* x) const noexcept;   // rightmost node in subtree

    // --- Search (return _nil when not found / past-the-end) ---
    Node* _findNode      (const K& key) const noexcept;
    Node* _lowerBoundNode(const K& key) const noexcept;  // first: key >= k
    Node* _upperBoundNode(const K& key) const noexcept;  // first: key >  k

    // --- Bulk tree operations ---
    void  _clearTree(Node* x) noexcept;
    // Deep-copies the subtree rooted at src from srcMap into this map.
    // Returns the new subtree root; newParent is the parent to assign.
    // Caller must invoke _rebuildList() once after the full tree is copied.
    Node* _copyTree(const RBTreeMap& srcMap, Node* src, Node* newParent);
    // Rebuilds _nil->next/_nil->prev and all node prev/next after a bulk copy.
    void  _rebuildList() noexcept;

    // --- Linked-list maintenance (O(1)) ---
    // Inserts newNode immediately after pred in the doubly-linked list.
    void _listInsertAfter(Node* pred, Node* newNode) noexcept;
    // Unlinks node from the doubly-linked list.
    void _listRemove(Node* node) noexcept;

    // --- Core insert (shared by insert and emplace) ---
    // Constructs a node from forwarded args, places it in the BST, wires the
    // linked list, then calls _insertFixup.  Returns the same pair contract as
    // the public insert/emplace.
    template <typename... Args>
    std::pair<iterator, bool> _insertNode(Args&&... args);
};

// ---------------------------------------------------------------------------
// Include the implementation so the compiler can instantiate the template.
// ---------------------------------------------------------------------------
#include "RBTreeMap.hpp"
