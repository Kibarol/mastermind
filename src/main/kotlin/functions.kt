fun main() {
    //lambda
    val l1: (Int) -> Int = {x: Int -> x*2}

    //lambda avec variable implicite it
    val l2: (Int) -> Int = {it*2}
}
fun isEven(nb: Int): String = if(nb % 2 ==0)"pair" else "impair"
