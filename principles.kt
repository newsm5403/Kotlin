import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow

fun part(sumAll: Long){
    when(sumAll){
        in 0..10_000 -> return println("${sumAll}원")
        in 10_000..100_000_000 -> return println("${sumAll/10000}만 ${sumAll%10000}원")
        in 100_000_000..1_000_000_000_000 -> return println("${sumAll/100_000_000}억 ${sumAll%100_000_000/10_000}만 ${sumAll%100_000_000%10_000}원")
    }
}

fun checkSum(price:Long, period: Int, term: Int, rates: Double,seedMoney: Long){
    var x:Int = 1
    val sum= price*12/term
    var totalmoney: Long = sum + seedMoney
    while( x <= period){
        val sumAll: Long = (totalmoney * (1 + rates / 100).pow(1)).toLong()
        print("$x 년째 가격: ")
        part(sumAll)
        totalmoney=sumAll+sum

        val profit: Double = sumAll/(seedMoney+sum*x).toDouble()*10
        getFormat(profit)
        x++
    }
}

fun getFormat(profit: Double){
    val DF = DecimalFormat("#.##")
    DF.roundingMode = RoundingMode.HALF_UP
    val FormatDouble = DF.format(profit)
    println("예상 수익률: $FormatDouble%")
}

class CIC{
    init{print("넣는 돈이 얼마입니까?: ") }
    private val price: Long= readLine()!!.toLong()
    init{print("입금 기간이 얼마나 됩니까?(년): ")}
    private val period: Int = readLine()!!.toInt()
    init{print("입금 주기가 얼마나 됩니까?(개월): ")}
    private val term: Int = readLine()!!.toInt()
    init{print("연리는 얼마나 됩니까?(%): ")}
    private val rates:Double= readLine()!!.toDouble()
    init{print("이미 투자한 돈은 얼마나 됩니까?(원): ")}
    private val seedMoney: Long = readLine()!!.toLong()
    var sum= price*period*12/term
    init{
        print("넣는 돈: ")
        part(price)
        println("입금 기간: ${period}년")
        println("입금 주기: ${term}개월")
        println("연리: ${rates}%")
        println("---------------------------------------------")
        print("원금: ")
        part(sum)
        print("기본자금: ")
        part(seedMoney)
        print("총투자자금: ")
        part(sum + seedMoney)
        println("---------------------------------------------")
        checkSum(price, period, term, rates,seedMoney)
    }
}

fun main() {
    var quit:String="something"
    while(quit!="q") {
        CIC()
        print("계속 하시려면 아무키나 누르세요. 중단하고 싶으면 q를 입력하세요.: ")
        quit= readLine()!!
    }
}

