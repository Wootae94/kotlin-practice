package basic

fun main(args: Array<String>) {
    val sum1 = sum1(1, 4)
    val sum2 = sum2(1, 4)

    println(sum1)
    println(sum2)

    // $ 표시로 문자열 변수 사용가능.
    val s1 = "x is $x"
    println(s1)
    x = 3
    val s2 = "${s1.replace("is", "was")}, but now is $x"
    println(s2)

    printIntString("4", "5")
    printIntString("4", "안녕하세요.")

    println(getStringLength("코틀린 연습"))
    println(getStringLength(20))

    println(describe("When 표현식입니다."))

    rangeFun(4)

    collectionsFun("apple", "bananan", "kiwi", "avocado")
}

fun sum1(a: Int, b: Int): Int {
    return a + b
}

// 함수 body가 식인 경우, return 생략가능
// 이런 경우, return 타입 생략
fun sum2(a: Int, b: Int) = a + b


// return 값 없을 경우, Unit. Unit은 생략가능
fun printKotlin1(): Unit {
    println("Unit Return")
}

fun printKotlin2() {
    println("Unit Return")
}

// val : 읽기 전용 변수
// 값의 할당이 1회만 가능
val a: Int = 1
val b = 2
// val c:Int
// c = 3

// var : mutable 변수
var x = 5


// 조건문. 조건식으로 줄여 쓸수 있다.
fun maxOf1(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun maxOf2(a: Int, b: Int) = if (a > b) a else b

// return값이 null이 나올 수 있는 경우, ?를 통해 nullable 표기
fun parseInt(str: String): Int? {
    return try {
        str.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}

fun printIntString(arg1: String, arg2: String) {
    val x: Int? = parseInt(arg1)
    val y: Int? = parseInt(arg2)

    if (x != null && y != null) {
        println(x * y)
    } else {
        println("either '$arg1' or '$arg2' in not a number")
    }
}

// 자동 타입 변환, 타입 체크만 해도 자동으로 타입 변환이 됨.
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // obj가 자동으로 String 타입으로 변환되어 length함수 사용가능
        return obj.length
    }
    return null
}

// when expression. 타입 구분, 변수 판단 모두 가능.
fun describe(obj: Any): String =
    when (obj) {
        // 값 비교
        1 -> "One"
        "Hello" -> "Greeting"
        // 타입 비교
        is Long -> "Long Type"
        !is String -> "Not a String Type"
        else -> "Unknown"
    }

// range활용
fun rangeFun(a: Int) {
    // 조건문
    if (a in 1..10) {
        println("fit in range")
    }
    // 반복문
    for (x in 1..5) {
        println(x)
    }
}

fun collectionsFun(str1: String, str2: String, str3: String, str4: String) {
    val listItems = listOf(str1, str2, str3, str4)
    val setItems = setOf(str1, str2, str3, str4)

    for (item in listItems) {
        println(item)
    }

    when {
        "orange" in setItems -> println("Orange is juicy.")
        "apple" in setItems -> println("Apple is fine too.")
    }

    // 람다식 가능.
    listItems.filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }

}
