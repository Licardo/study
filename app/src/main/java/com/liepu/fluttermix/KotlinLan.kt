package com.liepu.fluttermix

import android.os.Debug

/**
 *
 *
 * @Author: liepu
 * @CreateDate: 2021/5/29 9:48 AM.
 * @Description:  描述具体内容
 * @UpdateUser:  更新者
 * @UpdateDate: 2021/5/29 9:48 AM.
 * @UpdateRemark:  更新说明
 */
class KotlinLan {

    private fun testLabel(): String {

        val person = Person()
        val param = person.gender ?: return "ss"

        first@for (i in 1..30) {
            if (i == 3) {
                break
            }
        }

        listOf(1, 2, 3, 4, 5).forEach aaa@{
            if (it == 4) {
                return@aaa
            }
            print(it)
        }
        print("done")

        run loop@{
            listOf(2,3,4,5).forEach {
                if (it == 3) return@loop
                print(it)
            }
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Debug.getRuntimeStat("art.gc.gc-count")
        }

        var x = if (listOf(2,3).size > 3) 2 else 5

        when(1) {
            1 -> print("1")
            3 -> print("")
            4 -> {
                print("")
            }
            6,7,8 -> print("")
            String.hashCode() -> print("hash")
            in 2..3 -> print("")
            else -> {
                print("else")
            }
        }

        when {
            2 in 4..5 -> print("")
            else -> print("else")
        }

        when(val g = person.mode) {
            is String -> print("")
            "3" -> print(g)
        }

        person.mode = "3"
//        if (person::type.isInitialized) {
//            print("")
//        }

        val index: IntParse = object : IntParse {
            override fun parseInt(param: String): Int {
                return param.toInt()
            }
        }
        index.parseInt("1")

        val index1 = IntParse { it.toInt() }
        index1.parseInt("2")

        person.testAlias {i, j ->
            if (i > 0) {
                j
            } else {
                i == 0
            }
        }



        return "3"
    }

    fun interface IntParse {
        fun parseInt(param: String): Int
        fun parseFloat(param: String): Float {
            return param.toFloat()
        }
    }

    class Person : Body("s") {
        val gender get() = 3
//        幕后字段
        var mode: String = ""
            get() = field
            set(value) {
                field = value
            }

        lateinit var type: String

        fun test() {
            if (this::type.isInitialized) {
                print("")
            }
        }

        fun testAlias(alias: NoteAlias): Boolean {
            val p = 4
            return alias(p, true)
        }
    }

    open class Body(var name: String) {

        constructor(id: String, name: String): this(name) {
        }

        constructor(age: Int, name: String) : this(name) {

        }
    }


    fun main() {
        testLabel()
    }
}

// 类型别名
typealias NoteAlias = (i: Int, j: Boolean) -> Boolean
