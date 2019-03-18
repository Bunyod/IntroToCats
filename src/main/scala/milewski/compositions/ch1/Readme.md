# Notes
The key aspect of identity is that it is a unit with response to composition.


# Challenges

## Challenge 1
_Implement, as best as you can, the identity function in your favorite language (or the second favorite, if your favorite language happens to be Haskell)._

[Challenge1.scala](./Challenge1.scala)

## Challenge 2
_Implement the composition function in your favorite language. It takes two functions as arguments and returns a function that is their composition._

[Challenge2.scala](./Challenge2.scala)

## Challenge 3
_Write a program that tries to test that your composition function respects identity._

[Challenge3.scala](./Challenge3.scala)

## Challenge 4
_Is the world-wide web a category in any sense? Are links morphisms?_

With browser assistance. Pages are objects and navigability/reachability is morphisms. I can
navigate to any page from itself using reload. Since navigability using links from A->B->C is
transitive, there is a composition for it. Links aren't morphisms however, because
there isn't an actual link on every page to every other page that is reachable.

## Challenge 5
_Is Facebook a category, with people as objects and friendships as morphisms?_

No. Friend arrows are not guaranteed to compose.

## Challenge 6
_When is a directed graph a category?_

A directed graph would be a category if for all nodes k and all nodes j reachable from
k, there exists a path from k to j of length 1.
