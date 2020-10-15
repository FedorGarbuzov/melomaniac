import java.text.DecimalFormat
import kotlin.math.roundToInt

fun main() {
    val itemPrice = 1000.0
    val itemCount = 1
    val discount = 100
    val discountFirstLevel = 1000
    val discountSecondLevel = 10000
    val amount = 10010
    val melomaniac = false

    val totalPrice = itemPrice * itemCount
    totalBuyPrice(totalPrice, discountFirstLevel, discount, discountSecondLevel, amount, melomaniac)
}

fun totalBuyPrice(totalPrice: Double, discountFirstLevel: Int, discount: Int, discountSecondLevel: Int, amount: Int, melomaniac: Boolean) {
    when {
        amount < discountFirstLevel -> {
            notAmount(totalPrice, discountFirstLevel, discount, discountSecondLevel, melomaniac)
        }
        amount > discountFirstLevel && amount < discountSecondLevel -> {
            amountFirstLevel(totalPrice, discount, discountSecondLevel, melomaniac)
        }
        amount > discountSecondLevel -> {
            amountSecondLevel(totalPrice, melomaniac)
        }
    }
}

fun notAmount(totalPrice: Double, discountFirstLevel: Int, discount: Int, discountSecondLevel: Int, melomaniac: Boolean) {
    val formattedDouble = DecimalFormat(".00").format(totalPrice).toString()
    if (totalPrice > discountFirstLevel && totalPrice < discountSecondLevel) {
        val discountPrice = totalPrice - discount
        melomaniacOrNot(melomaniac, discountPrice)
    } else if (totalPrice > discountSecondLevel) {
        val discountPrice = totalPrice - (totalPrice / 100 * 5)
        melomaniacOrNot(melomaniac, discountPrice)
    } else if (melomaniac) {
        println("Сумма покупки: ${totalPrice - (totalPrice / 100 * 1).roundToInt()} руб. ${formattedDouble.takeLast(2)} коп.")
    } else {
        println("Сумма покупки: ${totalPrice.roundToInt()} руб. ${formattedDouble.takeLast(2)} коп.")
    }
}

fun amountFirstLevel(totalPrice: Double, discount: Int, discountSecondLevel: Int, melomaniac: Boolean) {
    if (totalPrice < discountSecondLevel) {
        val discountPrice = totalPrice - discount
        melomaniacOrNot(melomaniac, discountPrice)
    } else {
        val discountPrice = totalPrice - (totalPrice / 100 * 5)
        melomaniacOrNot(melomaniac, discountPrice)
    }
}

fun amountSecondLevel(totalPrice: Double, melomaniac: Boolean) {
    val discountPrice = totalPrice - (totalPrice / 100 * 5)
    melomaniacOrNot(melomaniac, discountPrice)
}

fun melomaniacOrNot(melomaniac: Boolean, discountPrice: Double) {
    if (melomaniac) {
        val melomaniacDiscountPrice = discountPrice - (discountPrice / 100 * 1)
        val formattedDouble = DecimalFormat(".00").format(melomaniacDiscountPrice).toString()
        println("Сумма покупки: ${melomaniacDiscountPrice.roundToInt()} руб. ${formattedDouble.takeLast(2)} коп.")
    } else {
        val formattedDouble = DecimalFormat(".00").format(discountPrice).toString()
        println("Сумма покупки: ${discountPrice.roundToInt()} руб. ${formattedDouble.takeLast(2)} коп.")
    }
}
