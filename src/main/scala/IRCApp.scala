import scala.io.StdIn.readLine

import java.io._
import java.net._
import java.util.Scanner

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import fs2.Stream

object IRCApp extends IOApp {

  val console = new Scanner(System.in)
  val server = "irc.freenode.net"

  val socket: Socket = new Socket(server, 6667)
  val writer = new PrintWriter(
    socket.getOutputStream(),
    true
  )
  val reader = new Scanner(
    socket.getInputStream()
  )

  def stream: Stream[IO, ExitCode] = {
    Stream.eval(IO {

      print("Nickname: ")
      val nickname = console.nextLine()

      print("Username: ")
      val username = console.nextLine()

      print("Channel to join: ")
      val channel = console.nextLine()

      write("NICK", nickname)
      write("USER", username + " 0  * : Scala IRC Client\r\n")

      write("JOIN", channel)

      while (reader.hasNext()) {
        val msg: String = reader.nextLine()
        println("<<<" + msg)
        if (msg.startsWith("PING")) {
          val ping = msg.split(" ", 2).tail.head
          write("PONG", ping)
        }
      }

      reader.close()
      writer.close()
      socket.close()

      ExitCode.Success
    })
  }

  def write(cmd: String, msg: String): Unit = {
    val fullMsg = cmd + " " + msg;
    println(">>> " + fullMsg)
    writer.print(fullMsg + "\r\n")
    writer.flush()
  }

  def run(args: List[String]): IO[ExitCode] = {
    stream.compile.drain.as(ExitCode.Success)
  }

}
