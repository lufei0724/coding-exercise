import org.scalatest.funsuite.AnyFunSuite

class MainSpec extends AnyFunSuite {

  test("helloWord should return Hello World") {
    assert(Main.helloWorld == "Hello World")
  }
}
