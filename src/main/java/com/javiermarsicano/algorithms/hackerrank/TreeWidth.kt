import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

class TreeNode {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

/*
 * Get the width of a binary tree
 *
 * Example:
 *
 *         0
 *        / \
 *       0   0
 *      / \
 *     0   0
 *     |1|1|1|  width = 3
 *
 * Complete the 'treeWidth' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts TreeNode head as parameter.
 */

fun treeWidth(head: TreeNode): Int {
    val widths = traverse(head)
    return return widths.first + widths.second
}

fun traverse(node: TreeNode): Pair<Int,Int> {
    var rightChildLeftW = 0
    var rightChildRightW = 0
    var leftChildLeftW = 0
    var leftChildRightW = 0

    var leftW = 0
    var rightW = 0
    node.left?.let {
        val result = traverse(it)
        leftChildLeftW = result.first + 1
        leftChildRightW = result.second - 1
    }
    node.right?.let {
        val result = traverse(it)
        rightChildLeftW = result.first - 1
        rightChildRightW = result.second + 1
    }
    if (rightChildLeftW > leftChildLeftW) {
        leftW = rightChildLeftW
    } else {
        leftW = leftChildLeftW
    }
    if (rightChildRightW > leftChildRightW) {
        rightW = rightChildRightW
    } else {
        rightW = leftChildRightW
    }
    return leftW to rightW
}


fun insertLevelInOrder(array: Array<String>, i: Int): TreeNode? {
    if ( i >= array.count() || array[i] == "NIL") { return null }
    var temp = TreeNode()
    temp.left = insertLevelInOrder(array, 2 * i + 1)
    temp.right = insertLevelInOrder(array, 2 * i + 2)
    return temp
}

fun main(args: Array<String>) {
    val input = generateSequence(::readLine)
    val lines: Array<String> = input.toList().toTypedArray()
    val root = insertLevelInOrder(lines, 0)!!
    val result = treeWidth(root)
    println(result)
}

