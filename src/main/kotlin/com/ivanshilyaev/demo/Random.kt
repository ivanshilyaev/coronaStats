package cloud.folium.mpr.test.random

import com.github.mangatmodi.randomjson.RandomJsonCreator
import com.github.mangatmodi.randomjson.config.RandomJsonConfig
import com.github.mangatmodi.randomjson.service.*
import net.andreinc.mockneat.MockNeat
import net.andreinc.mockneat.types.enums.IPv4Type
import java.net.URLEncoder
import java.util.*
import java.util.concurrent.ThreadLocalRandom

const val testProto = "http"

const val testUserAgent = "Mozilla/5.0 (Test)"

val config = RandomJsonConfig(
    RandomDouble.threadLocalRandom(),
    RandomInt.threadLocalRandom(),
    RandomString.charArray("eusbwopw".toCharArray(), 5),
    RandomBoolean.uniform(),
    RandomString.charArray("abcdefg".toCharArray(), 5)
)

fun randomIp(): String = MockNeat.threadLocal().ipv4s().type(IPv4Type.NO_CONSTRAINT).`val`()

fun randomHost(): String = MockNeat.threadLocal().ipv4s().type(IPv4Type.NO_CONSTRAINT).`val`()

fun randomPort(): Int = Random().nextInt(65536)

fun randomUrl(): String = MockNeat.threadLocal().urls().`val`()

fun randomPath(partsCount: Int = 3) = "/" +
        IntArray(partsCount).mapIndexed { i, _ -> randomStr(i + 1).toLowerCase() }.joinToString("/")

fun randomQuery(paramsCount: Int = 3) =
    IntArray(paramsCount).mapIndexed { i, _ -> "${randomStr(i + 1)}=${randomStr(7)}" }.joinToString("&")

fun randomEncodedUrl(): String = URLEncoder.encode(randomUrl(), "UTF-8")

fun randomStr(len: Int = 16): String {

    val randomChars = mutableListOf<Char>()

    for (i in 1..len) {
        val randomChar = chars[ThreadLocalRandom.current().nextInt(0, chars.size)]
        randomChars.add(randomChar)
    }

    return randomChars.joinToString("")
}

fun randomInt(max: Int = Int.MAX_VALUE) = ThreadLocalRandom.current().nextInt(1, max)

fun randomLong(max: Long = Long.MAX_VALUE) = ThreadLocalRandom.current().nextLong(1L, max)

fun randomJson(): String {
    val jsonCreator = RandomJsonCreator
        .fromNumberOfKeys(10, config, RandomTypeSelector.uniform())
    return jsonCreator.create()
}

private val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray()
