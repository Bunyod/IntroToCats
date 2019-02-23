
import akka.NotUsed
import akka.actor.{Actor, ActorSystem, Props}
import akka.stream.{ActorMaterializer, ClosedShape}
import akka.stream.scaladsl.{Balance, Broadcast, GraphDSL, Merge, RunnableGraph, Sink, Source}

import scala.util.Try

object ActorRefWithAck extends App {

  implicit val system = ActorSystem("sink")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val source: Source[Int, NotUsed] = Source(1 to 100)
  val actor = system.actorOf(Props[MyActor])
  source.runWith(Sink.actorRefWithAck(
    ref = actor,
    onInitMessage = Init,
    ackMessage = Ack,
    onCompleteMessage = "done"
  ))

//  import akka.stream.scaladsl.GraphDSL.Implicits._
//  val partial = RunnableGraph.fromGraph(GraphDSL.create() { implicit builder =>
//    val B = builder.add(Broadcast[Int](2))
//    val C = builder.add(Merge[Int](2))
//    val E = builder.add(Balance[Int](2))
//    val F = builder.add(Merge[Int](2))
//
//    Source.single(0) ~> B.in; B.out(0) ~> C.in(1); C.out ~> F.in(0)
//    C.in(0) <~ F.out
//
//    B.out(1).map(_ + 1) ~> E.in; E.out(0) ~> F.in(1)
//    E.out(1) ~> Sink.foreach(println)
//    ClosedShape
//  }).named("partial")
//  Source.single(0).via(partial).to(Sink.ignore)



}
case object Init

case object Ack

class MyActor extends Actor {
  def receive = {
    case Init =>
      println("Init")
      sender() ! Ack
    case Ack => println("Ack")
    case i: Int =>
      println(i)
      if ( i % 2 == 0) {
        Thread.sleep(1000)
        sender() ! Ack
      } else {
        Thread.sleep(2000)

        sender() ! Ack
      }
  }
}
