package com.example.developmentapp.screens.python

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.developmentapp.screens.BodyText
import com.example.developmentapp.screens.CodeBlock
import com.example.developmentapp.screens.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassesObjectsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Python — Classes & Objects", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier       = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                SectionCard("Defining a Class") {
                    CodeBlock(
                        "class Animal:\n" +
                        "    # Class variable — shared by ALL instances:\n" +
                        "    kingdom = 'Animalia'\n\n" +
                        "    # __init__ is the constructor (called on object creation):\n" +
                        "    def __init__(self, name: str, sound: str):\n" +
                        "        # Instance variables — each object has its own copy:\n" +
                        "        self.name  = name\n" +
                        "        self.sound = sound\n\n" +
                        "    # Instance method — first param is always 'self':\n" +
                        "    def speak(self):\n" +
                        "        return f'{self.name} says {self.sound}'\n\n" +
                        "    def __str__(self):          # called by print() and str()\n" +
                        "        return f'Animal({self.name})'\n\n" +
                        "    def __repr__(self):         # unambiguous representation\n" +
                        "        return f'Animal(name={self.name!r}, sound={self.sound!r})'"
                    )
                }
            }

            item {
                SectionCard("Creating and Using Objects") {
                    CodeBlock(
                        "# Create instances — __init__ is called automatically:\n" +
                        "dog = Animal('Rex', 'Woof')\n" +
                        "cat = Animal('Whiskers', 'Meow')\n\n" +
                        "# Access instance attributes:\n" +
                        "print(dog.name)          # 'Rex'\n" +
                        "print(cat.sound)         # 'Meow'\n\n" +
                        "# Call instance methods:\n" +
                        "print(dog.speak())       # 'Rex says Woof'\n\n" +
                        "# Access class variable via instance or class:\n" +
                        "print(dog.kingdom)       # 'Animalia'\n" +
                        "print(Animal.kingdom)    # 'Animalia'\n\n" +
                        "# Add a new attribute to an existing instance (Python allows this):\n" +
                        "dog.age = 3              # dog now has an 'age'; cat does not\n\n" +
                        "# Check type:\n" +
                        "isinstance(dog, Animal)  # True\n" +
                        "type(dog)                # <class '__main__.Animal'>"
                    )
                }
            }

            item {
                SectionCard("Class Methods and Static Methods") {
                    CodeBlock(
                        "class Counter:\n" +
                        "    total = 0              # class variable\n\n" +
                        "    def __init__(self):\n" +
                        "        Counter.total += 1\n" +
                        "        self.id = Counter.total\n\n" +
                        "    # @classmethod — first param is the CLASS, not the instance:\n" +
                        "    @classmethod\n" +
                        "    def get_total(cls):\n" +
                        "        return cls.total   # cls = Counter\n\n" +
                        "    # @staticmethod — no implicit first param; just a utility:\n" +
                        "    @staticmethod\n" +
                        "    def description():\n" +
                        "        return 'Counts instances'\n\n" +
                        "c1 = Counter()\n" +
                        "c2 = Counter()\n" +
                        "Counter.get_total()        # 2\n" +
                        "Counter.description()      # 'Counts instances'"
                    )
                    BodyText("@classmethod is often used as an alternative constructor. @staticmethod is for utility functions that logically belong to the class but don't need access to it.")
                }
            }

            item {
                SectionCard("@property — Computed Attributes") {
                    BodyText("@property lets you define a method that is accessed like an attribute. The getter, setter, and deleter are separate.")
                    CodeBlock(
                        "class Temperature:\n" +
                        "    def __init__(self, celsius: float):\n" +
                        "        self._celsius = celsius     # convention: _ = private\n\n" +
                        "    @property\n" +
                        "    def celsius(self) -> float:\n" +
                        "        return self._celsius\n\n" +
                        "    @celsius.setter\n" +
                        "    def celsius(self, value: float):\n" +
                        "        if value < -273.15:\n" +
                        "            raise ValueError('Below absolute zero!')\n" +
                        "        self._celsius = value\n\n" +
                        "    @property\n" +
                        "    def fahrenheit(self) -> float:  # read-only computed property\n" +
                        "        return self._celsius * 9/5 + 32\n\n" +
                        "t = Temperature(100)\n" +
                        "print(t.celsius)           # 100  (calls getter)\n" +
                        "t.celsius = 0              # calls setter\n" +
                        "print(t.fahrenheit)        # 32.0 (computed)"
                    )
                }
            }

            item {
                SectionCard("Inheritance") {
                    CodeBlock(
                        "class Dog(Animal):          # Dog inherits from Animal\n" +
                        "    def __init__(self, name: str, breed: str):\n" +
                        "        super().__init__(name, 'Woof')  # call parent __init__\n" +
                        "        self.breed = breed\n\n" +
                        "    # Override parent method:\n" +
                        "    def speak(self):\n" +
                        "        return f'{self.name} the {self.breed} barks!'\n\n" +
                        "    # Add new method:\n" +
                        "    def fetch(self):\n" +
                        "        return f'{self.name} fetches the ball!'\n\n" +
                        "rex = Dog('Rex', 'Labrador')\n" +
                        "rex.speak()                # 'Rex the Labrador barks!'\n" +
                        "rex.fetch()                # 'Rex fetches the ball!'\n" +
                        "isinstance(rex, Animal)    # True — Dog IS-A Animal\n" +
                        "isinstance(rex, Dog)       # True\n\n" +
                        "# Multiple inheritance:\n" +
                        "class Mixin:\n" +
                        "    def extra(self): return 'extra!'\n\n" +
                        "class PoliceDog(Dog, Mixin):\n" +
                        "    pass"
                    )
                }
            }

            item {
                SectionCard("What Does @ Mean? — Decorators") {
                    BodyText(
                        "The @ symbol applies a decorator — a function that wraps another function or class, adding or modifying behavior. " +
                        "@classmethod, @staticmethod, and @property are built-in decorators."
                    )
                    CodeBlock(
                        "# A decorator is just a function that takes a function and returns one:\n" +
                        "def log_call(func):\n" +
                        "    def wrapper(*args, **kwargs):\n" +
                        "        print(f'Calling {func.__name__}')\n" +
                        "        result = func(*args, **kwargs)\n" +
                        "        print(f'Done')\n" +
                        "        return result\n" +
                        "    return wrapper\n\n" +
                        "@log_call                   # equivalent to: greet = log_call(greet)\n" +
                        "def greet(name):\n" +
                        "    print(f'Hello, {name}')\n\n" +
                        "greet('Alice')\n" +
                        "# Output:\n" +
                        "# Calling greet\n" +
                        "# Hello, Alice\n" +
                        "# Done\n\n" +
                        "# Common decorators from standard library:\n" +
                        "import functools\n" +
                        "@functools.lru_cache(maxsize=128)   # memoize results\n" +
                        "def fib(n):\n" +
                        "    if n < 2: return n\n" +
                        "    return fib(n-1) + fib(n-2)"
                    )
                }
            }

            item {
                SectionCard("Monkey Patching — Changing Methods at Runtime") {
                    BodyText(
                        "In Python, methods are just attributes that happen to be functions. " +
                        "You can replace a method on an instance or on the class itself at runtime — this is called monkey patching."
                    )
                    CodeBlock(
                        "class Robot:\n" +
                        "    def greet(self):\n" +
                        "        return 'Hello!'\n\n" +
                        "r = Robot()\n" +
                        "print(r.greet())           # 'Hello!'\n\n" +
                        "# Replace method on the CLASS (affects all instances):\n" +
                        "def new_greet(self):\n" +
                        "    return 'Beep boop!'\n\n" +
                        "Robot.greet = new_greet\n" +
                        "print(r.greet())           # 'Beep boop!'\n\n" +
                        "# Replace method on ONE instance only:\n" +
                        "import types\n" +
                        "r2 = Robot()\n" +
                        "r2.greet = types.MethodType(lambda self: 'Hi!', r2)\n" +
                        "print(r2.greet())          # 'Hi!'\n" +
                        "print(r.greet())           # 'Beep boop!'  (class method)"
                    )
                    BodyText("Monkey patching is powerful but makes code hard to understand. It is common in testing (mocking) and frameworks, but should be used sparingly in production code.")
                }
            }

            item {
                SectionCard("Dunder Methods — Protocol Methods") {
                    BodyText("Dunder (double underscore) methods let your class integrate with Python's built-in operations.")
                    CodeBlock(
                        "class Vector:\n" +
                        "    def __init__(self, x, y):   self.x = x; self.y = y\n" +
                        "    def __add__(self, other):    return Vector(self.x + other.x, self.y + other.y)\n" +
                        "    def __mul__(self, scalar):   return Vector(self.x * scalar, self.y * scalar)\n" +
                        "    def __eq__(self, other):     return self.x == other.x and self.y == other.y\n" +
                        "    def __len__(self):           return 2\n" +
                        "    def __getitem__(self, i):   return (self.x, self.y)[i]\n" +
                        "    def __str__(self):           return f'({self.x}, {self.y})'\n" +
                        "    def __repr__(self):          return f'Vector({self.x}, {self.y})'\n\n" +
                        "v1 = Vector(1, 2)\n" +
                        "v2 = Vector(3, 4)\n" +
                        "print(v1 + v2)             # (4, 6)\n" +
                        "print(v1 * 3)              # (3, 6)\n" +
                        "print(v1 == Vector(1, 2))  # True\n" +
                        "print(len(v1))             # 2"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
