// RBTreeMap.hpp
// Template implementation — included at the bottom of RBTreeMap.h.
// Do not #include this file directly.

// ============================================================================
// Node constructors
// ============================================================================

// Forwards all args to std::pair<const K, V>.
// Accepts any form the pair supports: copy, move, (key, val), piecewise_construct.
template <typename K, typename V, typename Compare>
template <typename... Args>
RBTreeMap<K, V, Compare>::Node::Node(Args&&... args)
    : kv(std::forward<Args>(args)...)
    , color(Color::Red)
    , parent(nullptr), left(nullptr), right(nullptr)
    , prev(nullptr),   next(nullptr)
{}

// Sentinel-only constructor.  kv is default-constructed (never read on _nil).
// Requires K and V to be default-constructible.
template <typename K, typename V, typename Compare>
RBTreeMap<K, V, Compare>::Node::Node(NilTag) noexcept
    : kv()
    , color(Color::Black)
    , parent(nullptr), left(nullptr), right(nullptr)
    , prev(nullptr),   next(nullptr)
{}

// ============================================================================
// Default constructor
// ============================================================================

template <typename K, typename V, typename Compare>
RBTreeMap<K, V, Compare>::RBTreeMap()
    : _nil(nullptr), _root(nullptr), _size(0), _comp()
{
    _nil  = new Node(Node::NilTag{});
    _root = _nil;

    // Tree role: all "null" child/parent pointers equal _nil.
    // _nil->color == Black ensures fixup loops terminate correctly.
    _nil->parent = _nil;
    _nil->left   = _nil;
    _nil->right  = _nil;

    // List role: sentinel self-loop → begin() == end() when empty.
    _nil->next = _nil;
    _nil->prev = _nil;
}

// ============================================================================
// Destructor
// ============================================================================

template <typename K, typename V, typename Compare>
RBTreeMap<K, V, Compare>::~RBTreeMap()
{
    _clearTree(_root);
    delete _nil;
}

// ============================================================================
// _clearTree  (post-order delete; base case is the sentinel)
// ============================================================================

template <typename K, typename V, typename Compare>
void RBTreeMap<K, V, Compare>::_clearTree(Node* x) noexcept
{
    if (x == _nil) return;
    _clearTree(x->left);
    _clearTree(x->right);
    delete x;
}

// ============================================================================
// Capacity
// ============================================================================

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::size_type
RBTreeMap<K, V, Compare>::size() const noexcept { return _size; }

template <typename K, typename V, typename Compare>
bool RBTreeMap<K, V, Compare>::empty() const noexcept { return _size == 0; }

// ============================================================================
// iterator
// ============================================================================

template <typename K, typename V, typename Compare>
RBTreeMap<K, V, Compare>::iterator::iterator() noexcept
    : _node(nullptr), _nil(nullptr) {}

template <typename K, typename V, typename Compare>
RBTreeMap<K, V, Compare>::iterator::iterator(Node* node, const Node* nil) noexcept
    : _node(node), _nil(nil) {}

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::iterator::reference
RBTreeMap<K, V, Compare>::iterator::operator*() const noexcept
{ return _node->kv; }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::iterator::pointer
RBTreeMap<K, V, Compare>::iterator::operator->() const noexcept
{ return &_node->kv; }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::iterator&
RBTreeMap<K, V, Compare>::iterator::operator++() noexcept
{ _node = _node->next; return *this; }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::iterator
RBTreeMap<K, V, Compare>::iterator::operator++(int) noexcept
{ iterator tmp = *this; ++(*this); return tmp; }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::iterator&
RBTreeMap<K, V, Compare>::iterator::operator--() noexcept
{ _node = _node->prev; return *this; }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::iterator
RBTreeMap<K, V, Compare>::iterator::operator--(int) noexcept
{ iterator tmp = *this; --(*this); return tmp; }

template <typename K, typename V, typename Compare>
bool RBTreeMap<K, V, Compare>::iterator::operator==(const iterator& rhs) const noexcept
{ return _node == rhs._node; }

template <typename K, typename V, typename Compare>
bool RBTreeMap<K, V, Compare>::iterator::operator!=(const iterator& rhs) const noexcept
{ return _node != rhs._node; }

// ============================================================================
// const_iterator
// ============================================================================

template <typename K, typename V, typename Compare>
RBTreeMap<K, V, Compare>::const_iterator::const_iterator() noexcept
    : _node(nullptr), _nil(nullptr) {}

template <typename K, typename V, typename Compare>
RBTreeMap<K, V, Compare>::const_iterator::const_iterator(
        const Node* node, const Node* nil) noexcept
    : _node(node), _nil(nil) {}

// Implicit conversion from iterator.
// Accesses iterator::_node and ::_nil, which are private in iterator.
// This works because iterator declares 'friend class RBTreeMap', and
// const_iterator is a nested class of RBTreeMap — GCC/Clang grant enclosing-class
// friendship to all nested members.  If a compiler rejects it, add
// 'friend struct const_iterator;' inside struct iterator in RBTreeMap.h.
template <typename K, typename V, typename Compare>
RBTreeMap<K, V, Compare>::const_iterator::const_iterator(const iterator& it) noexcept
    : _node(it._node), _nil(it._nil) {}

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::const_iterator::reference
RBTreeMap<K, V, Compare>::const_iterator::operator*() const noexcept
{ return _node->kv; }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::const_iterator::pointer
RBTreeMap<K, V, Compare>::const_iterator::operator->() const noexcept
{ return &_node->kv; }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::const_iterator&
RBTreeMap<K, V, Compare>::const_iterator::operator++() noexcept
{ _node = _node->next; return *this; }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::const_iterator
RBTreeMap<K, V, Compare>::const_iterator::operator++(int) noexcept
{ const_iterator tmp = *this; ++(*this); return tmp; }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::const_iterator&
RBTreeMap<K, V, Compare>::const_iterator::operator--() noexcept
{ _node = _node->prev; return *this; }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::const_iterator
RBTreeMap<K, V, Compare>::const_iterator::operator--(int) noexcept
{ const_iterator tmp = *this; --(*this); return tmp; }

template <typename K, typename V, typename Compare>
bool RBTreeMap<K, V, Compare>::const_iterator::operator==(
        const const_iterator& rhs) const noexcept
{ return _node == rhs._node; }

template <typename K, typename V, typename Compare>
bool RBTreeMap<K, V, Compare>::const_iterator::operator!=(
        const const_iterator& rhs) const noexcept
{ return _node != rhs._node; }

// ============================================================================
// begin / end / cbegin / cend
// ============================================================================

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::iterator
RBTreeMap<K, V, Compare>::begin() noexcept
{ return iterator(_nil->next, _nil); }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::iterator
RBTreeMap<K, V, Compare>::end() noexcept
{ return iterator(_nil, _nil); }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::const_iterator
RBTreeMap<K, V, Compare>::begin() const noexcept
{ return const_iterator(_nil->next, _nil); }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::const_iterator
RBTreeMap<K, V, Compare>::end() const noexcept
{ return const_iterator(_nil, _nil); }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::const_iterator
RBTreeMap<K, V, Compare>::cbegin() const noexcept
{ return const_iterator(_nil->next, _nil); }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::const_iterator
RBTreeMap<K, V, Compare>::cend() const noexcept
{ return const_iterator(_nil, _nil); }

// ============================================================================
// _listInsertAfter  —  O(1) doubly-linked-list splice
// ============================================================================
//
// Inserts newNode immediately after pred in the ring.  Works uniformly for:
//   • new minimum  (pred == _nil → _nil->next becomes newNode)
//   • new maximum  (succ == _nil → _nil->prev becomes newNode)
//   • interior node
//   • first node ever inserted  (pred == succ == _nil, both pointers updated)

template <typename K, typename V, typename Compare>
void RBTreeMap<K, V, Compare>::_listInsertAfter(Node* pred, Node* newNode) noexcept
{
    Node* succ    = pred->next;
    newNode->prev = pred;
    newNode->next = succ;
    pred->next    = newNode;
    succ->prev    = newNode;
}

// ============================================================================
// _rotateLeft  —  standard CLRS left rotation
// ============================================================================
//
//       x                  y
//      / \                / \
//     α   y      →       x   γ
//        / \            / \
//       β   γ          α   β
//
// Setting y->left->parent = x is safe when y->left == _nil: it writes
// _nil->parent, which is never consulted by the fixup loop or any other code.

template <typename K, typename V, typename Compare>
void RBTreeMap<K, V, Compare>::_rotateLeft(Node* x) noexcept
{
    Node* y   = x->right;
    x->right  = y->left;
    y->left->parent = x;
    y->parent = x->parent;
    if (x->parent == _nil)
        _root = y;
    else if (x == x->parent->left)
        x->parent->left  = y;
    else
        x->parent->right = y;
    y->left   = x;
    x->parent = y;
}

// ============================================================================
// _rotateRight  —  mirror of _rotateLeft
// ============================================================================
//
//         x                y
//        / \              / \
//       y   γ    →       α   x
//      / \                  / \
//     α   β                β   γ

template <typename K, typename V, typename Compare>
void RBTreeMap<K, V, Compare>::_rotateRight(Node* x) noexcept
{
    Node* y   = x->left;
    x->left   = y->right;
    y->right->parent = x;
    y->parent = x->parent;
    if (x->parent == _nil)
        _root = y;
    else if (x == x->parent->right)
        x->parent->right = y;
    else
        x->parent->left  = y;
    y->right  = x;
    x->parent = y;
}

// ============================================================================
// _insertFixup  —  CLRS RB-INSERT-FIXUP
// ============================================================================
//
// Restores the five Red-Black invariants after BST insertion of a Red node z.
//
// Loop invariant: z is Red and is the only node that may violate RB property 4
// (a Red node may not have a Red parent).  All other RB properties hold.
//
// Three cases, mirrored for left vs right subtrees:
//
//   Case 1  Uncle y is Red:
//             Recolor parent and uncle Black, grandparent Red, walk z up.
//             Moves the potential violation two levels up toward the root.
//
//   Case 2  Uncle y is Black and z is the "inner" child:
//             Rotate z's parent toward the outer position; z takes parent's role.
//             Falls through into Case 3.
//
//   Case 3  Uncle y is Black and z is the "outer" child:
//             Recolor parent Black, grandparent Red, then rotate grandparent.
//             Eliminates the violation permanently.
//
// Termination: when z->parent is Black (includes z reaching the root because
// root->parent == _nil and _nil->color == Black).
// The final assignment forces the root to Black, fixing any Case-1 walk-up.

template <typename K, typename V, typename Compare>
void RBTreeMap<K, V, Compare>::_insertFixup(Node* z) noexcept
{
    while (z->parent->color == Color::Red) {
        if (z->parent == z->parent->parent->left) {
            Node* y = z->parent->parent->right;       // uncle
            if (y->color == Color::Red) {
                // ── Case 1 ──────────────────────────────────────────────────
                z->parent->color         = Color::Black;
                y->color                 = Color::Black;
                z->parent->parent->color = Color::Red;
                z = z->parent->parent;
            } else {
                if (z == z->parent->right) {
                    // ── Case 2 ──────────────────────────────────────────────
                    z = z->parent;
                    _rotateLeft(z);
                }
                // ── Case 3 ──────────────────────────────────────────────────
                z->parent->color         = Color::Black;
                z->parent->parent->color = Color::Red;
                _rotateRight(z->parent->parent);
            }
        } else {
            // Symmetric: z->parent is the right child of the grandparent.
            Node* y = z->parent->parent->left;        // uncle
            if (y->color == Color::Red) {
                // ── Case 1 (mirror) ─────────────────────────────────────────
                z->parent->color         = Color::Black;
                y->color                 = Color::Black;
                z->parent->parent->color = Color::Red;
                z = z->parent->parent;
            } else {
                if (z == z->parent->left) {
                    // ── Case 2 (mirror) ─────────────────────────────────────
                    z = z->parent;
                    _rotateRight(z);
                }
                // ── Case 3 (mirror) ─────────────────────────────────────────
                z->parent->color         = Color::Black;
                z->parent->parent->color = Color::Red;
                _rotateLeft(z->parent->parent);
            }
        }
    }
    _root->color = Color::Black;
}

// ============================================================================
// _insertNode  —  core insert shared by insert() and emplace()
// ============================================================================
//
// 1. Construct a new Red node from forwarded args.
// 2. BST descent to find the insertion point (parent y) while simultaneously
//    tracking the in-order predecessor (pred) and successor (succ):
//      • Going left  → current node is a candidate successor.
//      • Going right → current node is a candidate predecessor.
//    The last assignments at each level give the exact neighbors.
//    On duplicate key: destroy the new node, return existing iterator + false.
// 3. Wire tree parent/child links.
// 4. Wire doubly-linked list in O(1) via _listInsertAfter(pred, z).
// 5. Restore RB properties via _insertFixup(z).

template <typename K, typename V, typename Compare>
template <typename... Args>
std::pair<typename RBTreeMap<K, V, Compare>::iterator, bool>
RBTreeMap<K, V, Compare>::_insertNode(Args&&... args)
{
    Node* z      = new Node(std::forward<Args>(args)...);
    const K& key = z->kv.first;

    Node* pred = _nil;   // in-order predecessor (last right-descent ancestor)
    Node* succ = _nil;   // in-order successor  (last left-descent ancestor)
    Node* y    = _nil;   // future parent of z
    Node* x    = _root;

    while (x != _nil) {
        y = x;
        if (_comp(key, x->kv.first)) {
            succ = x;
            x    = x->left;
        } else if (_comp(x->kv.first, key)) {
            pred = x;
            x    = x->right;
        } else {
            // Duplicate: key == x->kv.first (neither comp is true).
            delete z;
            return { iterator(x, _nil), false };
        }
    }

    // Wire tree links
    z->parent = y;
    z->left   = _nil;
    z->right  = _nil;
    z->color  = Color::Red;

    if (y == _nil)
        _root = z;
    else if (_comp(key, y->kv.first))
        y->left  = z;
    else
        y->right = z;

    // Wire linked list — _listInsertAfter covers every case uniformly
    // (new minimum, new maximum, interior node, and the very first node)
    _listInsertAfter(pred, z);

    _insertFixup(z);
    ++_size;
    return { iterator(z, _nil), true };
}

// ============================================================================
// insert / emplace  (public)
// ============================================================================

template <typename K, typename V, typename Compare>
std::pair<typename RBTreeMap<K, V, Compare>::iterator, bool>
RBTreeMap<K, V, Compare>::insert(const value_type& value)
{ return _insertNode(value); }

template <typename K, typename V, typename Compare>
std::pair<typename RBTreeMap<K, V, Compare>::iterator, bool>
RBTreeMap<K, V, Compare>::insert(value_type&& value)
{ return _insertNode(std::move(value)); }

template <typename K, typename V, typename Compare>
template <typename... Args>
std::pair<typename RBTreeMap<K, V, Compare>::iterator, bool>
RBTreeMap<K, V, Compare>::emplace(Args&&... args)
{ return _insertNode(std::forward<Args>(args)...); }

// ============================================================================
// _listRemove  —  O(1) doubly-linked-list unlink
// ============================================================================
//
// Splices node out of the ring.  Works uniformly for first, last, and only
// elements because _nil is a permanent member of the ring.

template <typename K, typename V, typename Compare>
void RBTreeMap<K, V, Compare>::_listRemove(Node* node) noexcept
{
    node->prev->next = node->next;
    node->next->prev = node->prev;
}

// ============================================================================
// _transplant  —  replace subtree rooted at u with subtree rooted at v
// ============================================================================
//
// Updates u's parent to point to v instead of u.  Sets v->parent = u->parent
// unconditionally — when v == _nil this writes _nil->parent, which
// _deleteFixup reads to locate the sibling of a double-black sentinel.

template <typename K, typename V, typename Compare>
void RBTreeMap<K, V, Compare>::_transplant(Node* u, Node* v) noexcept
{
    if (u->parent == _nil)
        _root = v;
    else if (u == u->parent->left)
        u->parent->left  = v;
    else
        u->parent->right = v;
    v->parent = u->parent;
}

// ============================================================================
// _minimum  —  leftmost (smallest-key) node in a subtree
// ============================================================================

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::Node*
RBTreeMap<K, V, Compare>::_minimum(Node* x) const noexcept
{
    while (x->left != _nil) x = x->left;
    return x;
}

// ============================================================================
// _findNode  —  standard BST search; returns _nil when absent
// ============================================================================

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::Node*
RBTreeMap<K, V, Compare>::_findNode(const K& key) const noexcept
{
    Node* x = _root;
    while (x != _nil) {
        if      (_comp(key, x->kv.first)) x = x->left;
        else if (_comp(x->kv.first, key)) x = x->right;
        else                               return x;
    }
    return _nil;
}

// ============================================================================
// _deleteFixup  —  CLRS RB-DELETE-FIXUP
// ============================================================================
//
// x enters carrying a conceptual "extra black" credit from the deleted node.
// The loop redistributes or absorbs that credit until x is the root or Red.
//
// Four cases per side (x is left child; right side is mirrored):
//
//   Case 1  Sibling w is Red:
//             Recolor w Black, parent Red, rotate parent left.
//             w changes to a Black sibling → one of Cases 2–4 follows.
//
//   Case 2  w is Black, both of w's children are Black:
//             Recolor w Red (absorb one black from w), push credit up: x = parent.
//             If parent was Red, the outer loop exits and the final x->color=Black fixes it.
//
//   Case 3  w is Black, w's outer (right) child is Black, inner (left) child is Red:
//             Recolor w Red, w->left Black, rotate w right → w's outer child is now Red.
//             Falls through into Case 4.
//
//   Case 4  w is Black, w's outer (right) child is Red:
//             Copy parent's color to w, set parent and w->right Black, rotate parent left.
//             x = root to terminate the loop.
//
// Termination: Case 4 sets x = root; Case 2 walk-up reaches root or a Red node.
// Final x->color = Black eliminates the extra credit.
//
// Sentinel safety: x->parent is valid even when x == _nil because _transplant
// always sets _nil->parent when replacing a node with _nil.

template <typename K, typename V, typename Compare>
void RBTreeMap<K, V, Compare>::_deleteFixup(Node* x) noexcept
{
    while (x != _root && x->color == Color::Black) {
        if (x == x->parent->left) {
            Node* w = x->parent->right;               // sibling
            if (w->color == Color::Red) {
                // ── Case 1 ──────────────────────────────────────────────────
                w->color         = Color::Black;
                x->parent->color = Color::Red;
                _rotateLeft(x->parent);
                w = x->parent->right;
            }
            if (w->left->color  == Color::Black &&
                w->right->color == Color::Black) {
                // ── Case 2 ──────────────────────────────────────────────────
                w->color = Color::Red;
                x        = x->parent;
            } else {
                if (w->right->color == Color::Black) {
                    // ── Case 3 ──────────────────────────────────────────────
                    w->left->color = Color::Black;
                    w->color       = Color::Red;
                    _rotateRight(w);
                    w = x->parent->right;
                }
                // ── Case 4 ──────────────────────────────────────────────────
                w->color         = x->parent->color;
                x->parent->color = Color::Black;
                w->right->color  = Color::Black;
                _rotateLeft(x->parent);
                x = _root;
            }
        } else {
            // Symmetric: x is the right child.
            Node* w = x->parent->left;                // sibling
            if (w->color == Color::Red) {
                // ── Case 1 (mirror) ─────────────────────────────────────────
                w->color         = Color::Black;
                x->parent->color = Color::Red;
                _rotateRight(x->parent);
                w = x->parent->left;
            }
            if (w->right->color == Color::Black &&
                w->left->color  == Color::Black) {
                // ── Case 2 (mirror) ─────────────────────────────────────────
                w->color = Color::Red;
                x        = x->parent;
            } else {
                if (w->left->color == Color::Black) {
                    // ── Case 3 (mirror) ─────────────────────────────────────
                    w->right->color = Color::Black;
                    w->color        = Color::Red;
                    _rotateLeft(w);
                    w = x->parent->left;
                }
                // ── Case 4 (mirror) ─────────────────────────────────────────
                w->color         = x->parent->color;
                x->parent->color = Color::Black;
                w->left->color   = Color::Black;
                _rotateRight(x->parent);
                x = _root;
            }
        }
    }
    x->color = Color::Black;
}

// ============================================================================
// _eraseNode  —  core erase: CLRS RB-DELETE + linked-list unlink
// ============================================================================
//
// Linked-list correctness in the 2-child case:
//   y = minimum(z->right) is z's in-order successor, so in the list:
//     ... → z_prev → z → y → y_next → ...
//   CLRS moves y into z's tree position while y's list position is unchanged.
//   _listRemove(z) therefore gives: ... → z_prev → y → y_next → ... ✓
//
// The x->parent = y assignment (when y->parent == z) covers x == _nil:
//   _deleteFixup(x) reads x->parent to find x's sibling; _nil->parent must
//   point to y for that to work correctly.

template <typename K, typename V, typename Compare>
void RBTreeMap<K, V, Compare>::_eraseNode(Node* z) noexcept
{
    // Unlink from list first — z->next is still valid here.
    _listRemove(z);

    Node*  y              = z;
    Color  y_orig_color   = y->color;
    Node*  x;

    if (z->left == _nil) {
        x = z->right;
        _transplant(z, z->right);
    } else if (z->right == _nil) {
        x = z->left;
        _transplant(z, z->left);
    } else {
        y            = _minimum(z->right);
        y_orig_color = y->color;
        x            = y->right;

        if (y->parent == z) {
            x->parent = y;   // safe when x == _nil: _nil->parent = y for fixup
        } else {
            _transplant(y, y->right);
            y->right         = z->right;
            y->right->parent = y;
        }
        _transplant(z, y);
        y->left         = z->left;
        y->left->parent = y;
        y->color        = z->color;
    }

    delete z;

    if (y_orig_color == Color::Black)
        _deleteFixup(x);

    --_size;
}

// ============================================================================
// erase  (public)
// ============================================================================

// Erase by iterator — returns iterator to the element that followed pos.
// next_node is saved before _eraseNode to avoid reading freed memory.
template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::iterator
RBTreeMap<K, V, Compare>::erase(iterator pos)
{
    Node* next_node = pos._node->next;
    _eraseNode(pos._node);
    return iterator(next_node, _nil);
}

// const_iterator overload — extracts the mutable node pointer and delegates.
template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::iterator
RBTreeMap<K, V, Compare>::erase(const_iterator pos)
{
    Node* z         = const_cast<Node*>(pos._node);
    Node* next_node = z->next;
    _eraseNode(z);
    return iterator(next_node, _nil);
}

// Erase by key — returns number of elements removed (0 or 1).
template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::size_type
RBTreeMap<K, V, Compare>::erase(const K& key)
{
    Node* z = _findNode(key);
    if (z == _nil) return 0;
    _eraseNode(z);
    return 1;
}

// ============================================================================
// _maximum  —  rightmost (largest-key) node in a subtree
// ============================================================================

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::Node*
RBTreeMap<K, V, Compare>::_maximum(Node* x) const noexcept
{
    while (x->right != _nil) x = x->right;
    return x;
}

// ============================================================================
// _lowerBoundNode  —  first node whose key is >= k  (not less than k)
// ============================================================================
//
// Walk the BST.  Whenever x->key >= k, x is a valid answer — record it and
// try to find a smaller one by going left.  When x->key < k, discard x and
// go right.  The last recorded candidate is the result.

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::Node*
RBTreeMap<K, V, Compare>::_lowerBoundNode(const K& key) const noexcept
{
    Node* x      = _root;
    Node* result = _nil;        // _nil means "nothing found yet" == end()
    while (x != _nil) {
        if (_comp(x->kv.first, key)) {
            x = x->right;      // x->key < key: too small, go right
        } else {
            result = x;        // x->key >= key: candidate, try to go smaller
            x      = x->left;
        }
    }
    return result;
}

// ============================================================================
// _upperBoundNode  —  first node whose key is strictly > k
// ============================================================================
//
// Same pattern: whenever key < x->key, x is a candidate (it's > k);
// otherwise x->key <= k so we must go right.

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::Node*
RBTreeMap<K, V, Compare>::_upperBoundNode(const K& key) const noexcept
{
    Node* x      = _root;
    Node* result = _nil;
    while (x != _nil) {
        if (_comp(key, x->kv.first)) {
            result = x;        // key < x->key: candidate, try to go smaller
            x      = x->left;
        } else {
            x = x->right;     // x->key <= key: not strictly greater, go right
        }
    }
    return result;
}

// ============================================================================
// find
// ============================================================================

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::iterator
RBTreeMap<K, V, Compare>::find(const K& key)
{ return iterator(_findNode(key), _nil); }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::const_iterator
RBTreeMap<K, V, Compare>::find(const K& key) const
{ return const_iterator(_findNode(key), _nil); }

// ============================================================================
// lower_bound
// ============================================================================

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::iterator
RBTreeMap<K, V, Compare>::lower_bound(const K& key)
{ return iterator(_lowerBoundNode(key), _nil); }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::const_iterator
RBTreeMap<K, V, Compare>::lower_bound(const K& key) const
{ return const_iterator(_lowerBoundNode(key), _nil); }

// ============================================================================
// upper_bound
// ============================================================================

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::iterator
RBTreeMap<K, V, Compare>::upper_bound(const K& key)
{ return iterator(_upperBoundNode(key), _nil); }

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::const_iterator
RBTreeMap<K, V, Compare>::upper_bound(const K& key) const
{ return const_iterator(_upperBoundNode(key), _nil); }

// ============================================================================
// count / contains
// ============================================================================

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::size_type
RBTreeMap<K, V, Compare>::count(const K& key) const
{ return _findNode(key) != _nil ? 1 : 0; }

template <typename K, typename V, typename Compare>
bool RBTreeMap<K, V, Compare>::contains(const K& key) const
{ return _findNode(key) != _nil; }

// ============================================================================
// at  —  throws std::out_of_range when key is absent
// ============================================================================

template <typename K, typename V, typename Compare>
V& RBTreeMap<K, V, Compare>::at(const K& key)
{
    Node* n = _findNode(key);
    if (n == _nil) throw std::out_of_range("RBTreeMap::at: key not found");
    return n->kv.second;
}

template <typename K, typename V, typename Compare>
const V& RBTreeMap<K, V, Compare>::at(const K& key) const
{
    Node* n = _findNode(key);
    if (n == _nil) throw std::out_of_range("RBTreeMap::at: key not found");
    return n->kv.second;
}

// ============================================================================
// operator[]  —  insert default-constructed V if key is absent
// ============================================================================
//
// Delegates to _insertNode via piecewise_construct so the key is forwarded
// directly and V is default-constructed in-place.  When the key already
// exists, _insertNode returns the existing node (false flag) and the mapped
// value is returned by reference without modification.

template <typename K, typename V, typename Compare>
V& RBTreeMap<K, V, Compare>::operator[](const K& key)
{
    return _insertNode(std::piecewise_construct,
                       std::forward_as_tuple(key),
                       std::forward_as_tuple()).first->second;
}

template <typename K, typename V, typename Compare>
V& RBTreeMap<K, V, Compare>::operator[](K&& key)
{
    return _insertNode(std::piecewise_construct,
                       std::forward_as_tuple(std::move(key)),
                       std::forward_as_tuple()).first->second;
}

// ============================================================================
// clear  —  destroy all real nodes and reset to empty state
// ============================================================================

template <typename K, typename V, typename Compare>
void RBTreeMap<K, V, Compare>::clear() noexcept
{
    _clearTree(_root);
    _root      = _nil;
    _size      = 0;
    _nil->next = _nil;   // restore sentinel ring so begin() == end()
    _nil->prev = _nil;
}

// ============================================================================
// _copyTree  —  recursive pre-order tree copy
// ============================================================================
//
// Copies the subtree rooted at src from srcMap into this map's tree.
// prev/next list links are left as nullptr — _rebuildList threads them after.
//
// Exception safety: if any node allocation fails, the try/catch inside
// _copyTree calls _clearTree on the partially-built subtree before rethrowing,
// so no nodes are leaked regardless of where the failure occurs.

template <typename K, typename V, typename Compare>
typename RBTreeMap<K, V, Compare>::Node*
RBTreeMap<K, V, Compare>::_copyTree(const RBTreeMap& srcMap, Node* src, Node* newParent)
{
    if (src == srcMap._nil) return _nil;

    Node* n   = new Node(src->kv);   // copy-construct value_type; may throw
    n->color  = src->color;
    n->parent = newParent;
    n->left   = _nil;                // set before try so _clearTree(n) is safe
    n->right  = _nil;
    n->prev   = nullptr;
    n->next   = nullptr;

    try {
        n->left  = _copyTree(srcMap, src->left,  n);
        n->right = _copyTree(srcMap, src->right, n);
    } catch (...) {
        _clearTree(n);               // frees n, n->left (if built), n->right (_nil)
        throw;
    }
    return n;
}

// ============================================================================
// _rebuildList  —  iterative in-order traversal to wire prev/next
// ============================================================================
//
// After _copyTree all tree links are valid but prev/next are nullptr.
// This function threads the doubly-linked ring using BST successor logic:
//   • If x->right != _nil: successor = minimum(x->right)
//   • Else: walk up until we come from a left child (or reach _nil = end)
//
// O(n) amortized — each tree edge is traversed at most twice.

template <typename K, typename V, typename Compare>
void RBTreeMap<K, V, Compare>::_rebuildList() noexcept
{
    _nil->next = _nil;
    _nil->prev = _nil;
    if (_root == _nil) return;

    Node* prev = _nil;
    Node* x    = _minimum(_root);

    while (x != _nil) {
        // Wire x into the list after prev
        prev->next = x;
        x->prev    = prev;
        prev       = x;

        // Advance to in-order successor
        if (x->right != _nil) {
            x = _minimum(x->right);
        } else {
            Node* y = x->parent;
            while (y != _nil && x == y->right) { x = y; y = y->parent; }
            x = y;
        }
    }

    // Close the ring: last real node → _nil
    prev->next = _nil;
    _nil->prev = prev;
}

// ============================================================================
// Copy constructor
// ============================================================================
//
// Allocates a fresh sentinel, deep-copies the tree, then rebuilds the list.
// If _copyTree throws partway through, the try/catch frees the partial tree
// and the sentinel before rethrowing — no memory is leaked.

template <typename K, typename V, typename Compare>
RBTreeMap<K, V, Compare>::RBTreeMap(const RBTreeMap& other)
    : _nil(new Node(Node::NilTag{}))
    , _root(nullptr)
    , _size(0)
    , _comp(other._comp)
{
    _nil->parent = _nil->left = _nil->right = _nil->next = _nil->prev = _nil;
    _root = _nil;   // valid empty state before _copyTree attempts allocation
    try {
        _root = _copyTree(other, other._root, _nil);
        _size = other._size;
        _rebuildList();
    } catch (...) {
        _clearTree(_root);   // _root may be _nil (no-op) or a partial tree
        delete _nil;
        throw;
    }
}

// ============================================================================
// Move constructor
// ============================================================================
//
// Steals other's sentinel and data in O(1).  All of other's nodes already
// point to other's _nil — after swapping sentinels they point to this->_nil.
// No per-node pointer fixup needed.
//
// A fresh sentinel is allocated for other so its destructor runs correctly.
// If that allocation throws inside a noexcept constructor, std::terminate is
// called — acceptable (matches std::map behaviour).

template <typename K, typename V, typename Compare>
RBTreeMap<K, V, Compare>::RBTreeMap(RBTreeMap&& other) noexcept
    : _nil(new Node(Node::NilTag{}))
    , _root(nullptr)
    , _size(0)
    , _comp(std::move(other._comp))
{
    _nil->parent = _nil->left = _nil->right = _nil->next = _nil->prev = _nil;
    _root = _nil;

    // Swap sentinels: this gets other's (nodes point to it), other gets ours (empty)
    std::swap(_nil,  other._nil);
    std::swap(_root, other._root);  // other._root becomes our old _nil (empty tree)
    std::swap(_size, other._size);  // other._size becomes 0
    // other._nil->next/prev are already self-looped (we initialised them above)
}

// ============================================================================
// Copy assignment  —  copy-and-swap (strong exception guarantee)
// ============================================================================
//
// If the copy constructor throws, *this is left unchanged.
// On success, tmp's destructor frees our old data.

template <typename K, typename V, typename Compare>
RBTreeMap<K, V, Compare>&
RBTreeMap<K, V, Compare>::operator=(const RBTreeMap& other)
{
    if (this == &other) return *this;
    RBTreeMap tmp(other);            // may throw; *this is untouched if it does
    std::swap(_nil,  tmp._nil);
    std::swap(_root, tmp._root);
    std::swap(_size, tmp._size);
    std::swap(_comp, tmp._comp);
    return *this;
}

// ============================================================================
// Move assignment
// ============================================================================
//
// Frees current data, resets the sentinel ring to empty, then swaps sentinels
// with other — O(1), no per-node pointer fixup needed.
// Leaves other as a valid empty map (its _nil is our old empty sentinel).

template <typename K, typename V, typename Compare>
RBTreeMap<K, V, Compare>&
RBTreeMap<K, V, Compare>::operator=(RBTreeMap&& other) noexcept
{
    if (this == &other) return *this;

    _clearTree(_root);          // free all our current nodes
    _nil->next = _nil;          // dangle-free: sentinel ring back to self-loop
    _nil->prev = _nil;

    std::swap(_nil, other._nil);  // steal other's sentinel (nodes point to it)
    _root       = other._root;    // steal root
    _size       = other._size;    // steal size
    _comp       = std::move(other._comp);

    other._root = other._nil;     // leave other with a valid empty tree
    other._size = 0;
    // other._nil is our old empty sentinel with self-loop next/prev ✓

    return *this;
}
