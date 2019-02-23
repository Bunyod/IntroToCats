#So why use Scala?

Having a language which helps write complex code with less bugs is less downtime and happier users. Writing highly concurrent, low-latency applications with the help of one of the Scala concurrency toolkits means bigger profits for the company.


Why we need to use some functional libraries? Isn’t Scala already a functional language?
Scala is already functional, but a lot of the machinery for doing pure functional programming is missing in the standard library. Plenty of people do heavy FP without Scalaz or Cats, but eventually you may find yourself reinventing some of the things those libraries provide.

Functional programming promises to raise the abstraction of code and to help to escape the micromanaging prevalent in imperative programming. Concepts like Immutability are essential to many functional languages and promise to remove whole classes of bugs (race conditions, etc), improve scalability (locking becomes unnecessary) and bring new capabilities (versioning stores over destructive ones)


The core of the argument made by Rich (and others) against Scala as a functional language goes something like this:

Mutable variables as first-class citizens of the language
Uncontrolled side-effects (ties in with the first point)
Mutable collections and other imperative libraries exist on equal footing
Object-oriented structures (class inheritance, overloading, etc)
Verbosity


Recursion
Most if not all of modern programming languages support the notion of recursion However it plays a much bigger part in functional programming than in imperative programming. That is because when programming in a functional way, iterating over data structure recursively is not just much more elegant, but also the only way to iterate without invoking side-effects.




Algebraic Data Types
Algebraic data types? Aw man, what’s this fancy maths stuff? I just want to program cool stuff! Alright! I won’t go into too much detail here, so bear with me for a moment! Okay, so most functional languages allow you to define simple data types. These ADTs are simple data cointainers that can be defined recursively. They can be easily constructed and deconstructed and usually come with built in structural equality checking.

All of this allows us to utilize a technique called “Pattern matching”. Pattern matching is a kind of switch-case on steroids, it can do type-testing, it can check exhaustiveness and it can destructure it’s arguments. Let’s have a look at an example written in Scala:

val res = arr match {
  case Array(0) => "0"
  case Array(x, y) => x + " > " + y
  case Array(0, _*) => "0 > ..."
  case _ => "..."
}

This is just a rather simple example, but I’m sure you can imagine how powerful the match expression can be. An ADT can be anything by the way, from Tuples to Lists, to Records. So Pattern matching is extremely useful because we can decompose any kind of data structure by its shape instead of its contents.

With pattern matching navigating and decomposing data structures becomes very convenient, with a compact syntax.



What is the difference between Cats and Scalaz?
Cats and Scalaz have the same goal: to facilitate pure functional programming in Scala applications. 

Scalaz or Cats are libraries, think of them like a swiss army knife. You may only need one or two core data structures, or you may want to use tons of them, it really depends on the problem. My gateway drug into using Scalaz was needing to easily work with Future[Option[String]] types, without having to nest maps. Those are called Monad Transformers, and make life easier.

You don't extend Monad, you provide a type class instance. TypeClasses

Eh, sort of. Cats/Scalaz will be on the classpath, so it may cause issues if another library uses a different version of Cats. But that is the case with any scala library. However it's up to you if you want to return a Scalaz type class or just always go to a standard library class.


What is the difference between Cats and Scalaz? Which should I choose?
Technical comparison: https://github.com/fosskers/scalaz-and-cats


ScalaZ

Started: 2009 
Watch: 279
Star: 4008
Fork: 673
Contributors: 179

Cats:
Started: 2015
Watch: 207
Star: 2980
Fork: 757
Contributors: 286


Scala Cats is a library which provides abstractions for functional programming in the Scala.

How can we integrate Cats easily to existing project?

Foce us to learn functional programming paradigms (abstraction, patterns).

I saw some codes in our project which can be written in a clear way with monad transformers.


==============================================================================================

About Laws 
We covered lots of things around laws today. Why do we need laws anyway?

Laws are important because laws are important is a tautological statement, but there’s a grain of truth to it. Like traffic laws dictating that we drive on one side within a patch of land, some laws are convenient if everyone follows them.

What Cats/Haskell-style function programming allows us to do is write code that abstracts out the data, container, execution model, etc. The abstraction will make only the assumptions stated in the laws, thus each A: Monoid needs to satisfy the laws for the abstracted code to behave properly. We can call this the utilitarian view.

Even if we could accept the utility, why those laws? It’s because it’s on HaskellWiki or one of SPJ’s papers. They might offer a starting point with an existing implementation, which we can mimic. We can call this the traditionalist view. However, there is a danger of inheriting the design choices or even limitations that were made specifically for Haskell. Functor in category theory, for instance, is a more general term than Functor[F]. At least fmap is a function that returns F[A] => F[B], which is related. By the time we get to map in Scala, we lose that because of type inference.

Eventually we should tie our understanding back to math. Monoid laws correspond with the mathematical definition of monoids, and from there we can reap the benefits of the known properties of monoids. This is relevant especially for monoid laws, because the three laws are the same as the axioms of the category, because a monoid is a special case of a category.

For the sake of learning, I think it’s ok to start out with cargo cult. We all learn language through imitation and pattern recognition.

==============================================================================================

Functor
Applicatives
Monads
Monoids
Semigroups
Validated
Kleisli
Cartesian
SO MANY ACRYONYMS!!!
==============================================================================================

Typeclasses

A type class is an interface or API that represents some functionality, which we want to implement.

Type Class Instances 


They are behaviours that can be "inherited" by your code.

Each of the type class (e.g. functors, monoids, monads etc) are gowerned by laws

-----

Type Class Instances provides the implementation for the types

Type class Interface is expose the functionality for the user. 

==============================================================================================

Semigroups


trait Semigroup[A] {
	def combine(x: A, y: A): A
}

general structure to define things that can be combined


*Cats provides default implementations* developers (like you & me) need to provide implementations that conform to the traits.

---

According to mathematicians, semigroups are called algebraic structure and algebraic structure means 
a set and operation (S,+)

Sets are:

-> Integers
-> Natural Numbers and more.

Algebraic Structure Properties:
-> Closure
-> Associativity


Closure:


x,y E Z

x + y = xy

xy E Z

Example:

Set of integers: {1,2,3,4,...n}
Select two elements: {4,5}
Operation: (+) Addition
Result: 9

Associativity:
x,y,z E Z
(x + y) + z = x + (y + z)

Example:

Set of integers: {1,2,3,4,...n}

Select three elements: {3,4,5}
Operation: (+) Addition

(3 + 4) + 5 = 3 + (4 + 5)

Result: 12 = 12


==============================================================================================
Monoids

trait Monoid[A] extends Semigroup[A] {
	def empty: A
	def combine(x: A, y: A): A
}

general structure to define things that can be combined and has a "default" element. 

Monoid must be a semigroup with identity element but not vice versa

Properties:

-> Closure
-> Associativity
-> Identity Element


Identity Element

x E Z
x + e = x
Example:

Set of integers: {1,2,3,4,...n}
Select one element: {4}
Operation: (+) Addition
4 + 0 = 4

Integer subtraction, for example, is not a monoid because subtraction is not associative:

(1 - 2) - 3
// res15: Int = -4

1 - (2 - 3)
// res16: Int = 2

==============================================================================================
Use case for Monoids & Semigroups

They are good for combining 2 or more things of a similar nature. 

data-type-a     data-type-b
		data-stream
==============================================================================================
Functors

trait Functor[F[_]] {
	def map[A,B](fa: F[A])(f: A => B): F[B]
}

General structure to represent something that can be mapped over. If you have been using Lists, Options, Eithers, Futures in Scala, you have been using functors. 

!!! They are very common structures indeed :) !!!

>import cats._, data._, implicits._
>Functor[List].lift((x: Int) => x + 1)
//res0: List[Int] => List[Int]
>res0(List(1))
//res1: List[Int] = List(2)
==============================================================================================
Monads

Monads are meant for SEQUENCING COMPUTATIONS

someList.flatMap(element =>
	someOtherList.map(element2 =>
		(element, element2)
	)
)

* No tuples generated if either "someList" or "someOtherList" is empty *

Monads allows for short-circuiting of computations

Quick summary

Writers - information can be carried along with the computation

Readers - compose operations that depend on some input

State - allows state to be "propagated"

Eval - abstracts over eager vs lazy evalutation

==============================================================================================
Monads example

> List(1,2,3) >>= (value => List(value+1))
res0: List[Int] = List(2,3,4)



">>=" is also known as "bind" (in Cats, its really "flatMap")

def >>=[A,B](fa: F[A])(f: A => B): F[B] = flatMap(fa)(f)


Typeclassses allows you to define re-usable code by lifting functions.

Monad[List].lift((x: Int) => x + 1)(List(1,2,3)
> res0: List[Int] = List(2,3,4)


Monad[List].pure(4)
> res2: List(4)

This is to lift values into a context, in this case Monadic context.
==============================================================================================
Applicative

Applicatives allows for functions to be lifted over a structure (Functor).

Because the function and the value it's being applied to both have structures, hence its needs to be combined.
==============================================================================================

Monad Transformers


Effectful Monads aka Eff-Monads

Effectful Monads

An alternative to Monad Transformers

==============================================================================================

The Monad Transformer Classes
By convention, in Cats a monad Foo will have a transformer class called FooT. In fact, many monads in Cats are defined by combining a monad transformer with the Id monad. Concretely, some of the available instances are:

cats.data.OptionT for Option;
cats.data.EitherT for Either;
cats.data.ReaderT for Reader;
cats.data.WriterT for Writer;
cats.data.StateT for State;
cats.data.IdT for the Id monad.

Monad transformer
From Wikipedia, the free encyclopedia

In functional programming, a monad transformer is a type constructor which takes a monad as an argument and returns a monad as a result.

Monad transformers can be used to compose features encapsulated by monads – such as state, exception handling, and I/O – in a modular way. Typically, a monad transformer is created by generalising an existing monad; applying the resulting monad transformer to the identity monad yields a monad which is equivalent to the original monad (ignoring any necessary boxing and unboxing).

==============================================================================================
Cats:

Started by Erik Osheim

100 Pull requests in the first week


Goals:

Small
Modular
Friendly to newcomers
Efficient
Not willing to compromise on Principals
Willing to compromise on Principals
Clean, consistent source code
Everything is serializable
Scaladoc all the thing
SEO

Unpleasant story


Typeclasses
(cats-core)

The usual suspects
- Functor, Applicative, Monad, Traverse
Inherited from non/algebra
- Monoid, Semigroup
Missing (good):
- Kan, Adjunction, Lens, Representable


#Cats (vs #Scalaz):
🔹CoC
🔹Friendly (docs, English)
🔹Strict by default
🔹Modular + ecosystem (vs monolithic)
🔹Good citizen (e.g. Either vs \/)


If you're referring to Scalaz, that's not true, AFAIK 7.0.x is incompatible with 7.1.x, which is incompatible with 7.2.x

Zero p project


https://blog.colinbreck.com/integrating-akka-streams-and-akka-actors-part-i/
https://blog.colinbreck.com/integrating-akka-streams-and-akka-actors-part-ii/
https://blog.colinbreck.com/integrating-akka-streams-and-akka-actors-part-iii/
https://blog.colinbreck.com/integrating-akka-streams-and-akka-actors-part-iv/