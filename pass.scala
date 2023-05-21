object Solution {
  def strongPasswordChecker(password: String): Int = {
    // 各条件の存在を管理する連想配列
    val conditions = collection.mutable.Map(
      "lower" -> false,
      "upper" -> false,
      "digit" -> false
    )

    // パスワードの各文字を調べて条件の存在を更新
    for (char <- password) {
      if (char.isLower)
        conditions("lower") = true
      else if (char.isUpper)
        conditions("upper") = true
      else if (char.isDigit)
        conditions("digit") = true
    }

    // 各条件の不足をチェックし、カウントする
    val requiredConditions = Set("lower", "upper", "digit")
    var missingConditions = requiredConditions.diff(conditions.keySet).size

    // パスワードの長さに応じて不足条件をカウントする
    if (password.length < 6)
      missingConditions += 6 - password.length
    else if (password.length > 20)
      missingConditions += password.length - 20

    // 3つ以上の連続した同じ文字の検出
    val repeatedCharacters = password.sliding(3).collect {
      case s if s.distinct.length == 1 => s.charAt(0)
    }
    missingConditions += repeatedCharacters.size

    // パスワードがすでに強力な場合は 0 を返す
    if (missingConditions == 0)
      return 0

    // パスワードが短い場合の追加条件の数を修正
    //ここが間違っている
    if (password.length < 6) {
      missingConditions = (6 - password.length).max(missingConditions) // Add condition for short passwords
    } else if (password.length == 1) {
      missingConditions = 5 // Additional condition for single-character passwords
    }
    // 不足している条件の数を返す
    missingConditions
  }
}
