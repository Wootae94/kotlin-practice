package basic

fun main() {
    ifFun(4, 5)
    whenFun(2)
    forLoop()

}

fun ifFun(a: Int, b: Int) {
    // if문이 식으로 사용되는 경우, 반드시 else와 함께.
    val max1 = if (a > b) a else b

    // if문의 branches들이 블록을 가질 수 있음.
    // 블록 마지막 구문이 반환됨.
    val max2 = if (a > b) {
        print("Choose a")
        a
    } else {
        print("Choose b")
        b
    }
    println(max1)
    println(max2)

    // 해당 기능들을 쓰면 되기에, 삼항연산자가 없음.
}

fun whenFun(x: Int) {
    // 각각의 branches의 조건문이 만족할때까지, 위에서 순차적으로 인자를 비교함.
    // 조건 -> 결과
    when (x) {
        1 -> println("x == 1")
        2 -> println("x == 2")
        else -> {
            print("x is neither 1 nor 2")
        }
    }

    // if문과 마찬가지로 식으로 진행할 경우, else 필수
    // boolean같이 컴파일러가 else를 판단할 수 있는경우는 없어도 됨.
    var res = when (x) {
        100 -> "A"
        90 -> "B"
        80 -> "C"
        70 -> "D"
        else -> "F"
    }
    println(res)

    // 여러조건을 같은 방식으로 처리할 경우, 조건문에 ',' 사용

    when (x) {
        0, 1 -> println("x == 0 or x == 1")
        else -> println("otherwise")
    }

    // branch의 조건문에 함수나 식을 사용할 수 있다.
    val s = "8"
    when (x) {
        parseInt(s) -> println("s encodeded x")
        1 + 3 -> print("4")
        else -> print("s does not encode x")
    }

    // range나 collection에 in이나 !in등으로 범위 검사도 가능
    val vaildNumbers = listOf(3, 6, 9)
    when (x) {
        in vaildNumbers -> println("x is valid")
        in 1..10 -> println("x is in range")
        !in 1..10 -> println("x is outside the range")
        else -> println("none of the above")
    }

    val a = "prefixTest"
    println( hasPrefix(a))

}

// is나 !is로 타입 검사 가능. 스마트 캐스트 적용
fun hasPrefix(a: Any) = when (a) {
    is String -> a.startsWith("prefix")
    else -> false
}

fun forLoop() {

    // For루프에서 인덱스를 쓰고 싶을때, indices와 withIndex가 있다.
    val array = arrayOf("가","나","다","라")
    for (i in array.indices) {
        println("$i: ${array[i]}")
    }

    for ((index, value) in array.withIndex()) {
        println("$index: $value")
    }

}
