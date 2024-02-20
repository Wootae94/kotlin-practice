package test


/*
* 상속여부에 따른 쿼리 파라미터 문자열 조합.
* @param currentQueryParam 쿼리 파라미터 문자열 (nullable)
* @param parentQueryParam 상우 쿼리 파라미터 문자열 (nullable)
* @param isInheritable 상속여부 (non-nullable)
* @return 조합된 유효한 쿼리 파라미터 문자열 (non-nullable)
* */
fun main() {
    val testCases = listOf(
        Triple("division1=상의", null, true),
        Triple("division1= 상의", null, false),
        Triple(null, "division1=상의", true),
        Triple(null, "division1=상의 ", false),
        Triple("keywords=반팔티&ids=1,2", "division1=상의", true),
        Triple("keywords=반팔티&ids=1,2", "division1=상의", false),
        Triple("division1=상의&&keywords=반팔티&ids=1,2,", "division1=상의", true),
        Triple("division1=상의&&keywords=반팔티&ids=1,2,", "division1=하의&keywords=청바지&name=홍길동", true),
        Triple("division1=상의&&keywords=반팔티&ids=1,2,", "division1=하의", true),
        Triple("division1=상의&&keywords=반팔티&ids=1,2,", "division1=하의,상의", true),
        Triple("division1=상의&&keywords=반팔티&ids=1,2,", "division1=하의,상의,", true),
        Triple("division1=상의&&keywords=반팔티&ids=1,2,", "division1=상의", false)
    )

    for ((currentQueryParam, parentQueryParam, isInheritable) in testCases) {
        val result = mergeQueryParams(currentQueryParam, parentQueryParam, isInheritable)
        println("Input: currentQueryParam = \"$currentQueryParam\", parentQueryParam = \"$parentQueryParam\", isInheritable = $isInheritable")
        println("Output: $result")
        println()
    }
}

fun mergeQueryParams(
    currentQueryParam: String?, parentQueryParam: String?, isInheritable: Boolean
): String {
    if (isInheritable) {
        val combinedParams = mutableMapOf<String, String>()
        parentQueryParam?.let {
            filterValidQueryParameter(it).split("&").associate {
                val (key, value) = it.split("=")
                key to value
            }.let { combinedParams.putAll(it) }
        }
        currentQueryParam?.let {
            filterValidQueryParameter(it).split("&").forEach { pair ->
                val (key, value) = pair.split("=")
                combinedParams[key] = combinedParams[key]?.let { existingValue ->
                    if (value !in existingValue.split(",")) "$existingValue,$value" else existingValue
                } ?: value
            }
        }
        return combinedParams.entries.joinToString("&") { "${it.key}=${it.value}" }
    } else {
        return currentQueryParam?.let { filterValidQueryParameter(it) } ?: ""
    }
}


fun filterValidQueryParameter(query: String?): String {
    // 쿼리 문자열이 null이거나 빈 문자열인 경우 유효하지 않음
    if (query.isNullOrBlank()) return ""

    val keyValuePairs = query.split("&") // &를 기준으로 문자열을 나눔
    val validKeyValuePairs = mutableListOf<String>()

    // 각 키-값 쌍에 대해 유효성 검사
    for (pair in keyValuePairs) {
        val keyValue = pair.split("=")
        // 키-값 쌍이 정확히 2개인 경우에만 유효성 검사 진행
        if (keyValue.size == 2) {
            val key = keyValue[0].trim() // 키에서 공백 제거
            var value = keyValue[1].trim() // 값에서 공백 제거

            // 값이 쉼표로 구분된 여러 값인 경우
            if (value.contains(",")) {
                // 각 값들 사이의 빈 값을 제거하여 필터링
                value = value.split(",").filter { it.isNotBlank() }.joinToString(",")
            }

            // 키와 값이 빈 값이 아닌 경우에만 유효한 키-값 쌍으로 추가
            if (key.isNotEmpty() && value.isNotEmpty()) {
                validKeyValuePairs.add("$key=$value")
            }
        }
    }

    // 유효한 키-값 쌍들을 &로 연결하여 하나의 문자열로 반환
    return validKeyValuePairs.joinToString("&")
}

