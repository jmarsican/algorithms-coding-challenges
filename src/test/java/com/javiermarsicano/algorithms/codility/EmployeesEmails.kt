package com.javiermarsicano.algorithms.codility

import junit.framework.Assert.*
import org.junit.Test

/**
You are given a list of names of new employees in a company. You need to generate a company email address for each of them.
The name of each person consists of two or three parts: a first name, an optional middle name, and a last name, separated
by spaces. Each part can include only English letters (but the last nam, may additionally contain hyphens). The name of the
company also consists only of English letters.
Each address must use the format 'First.Last@Company.com where
• First is the first name,
• Last is the last name, truncated to at most 8 initial letters,
• Company is the company name.

The address should be in lower case and should not contain hyphens.
Note that hyphens should be removed before truncating the last name.
Additionally, if more than one person would have the same email address, differentiate their addresses by adding subsequent
integers (starting from the second address and number 2) before, the "@" sign. For example, if there are three people who
would have the address " jd@company.com", they should be assigned addresses "jd@company.com", " jd2@company.com" and
"jd3@company.com" respectively.

Write a function:
fun solution(S: String, L: String): String
that, given a string S containing a list of names separated by the characters "; "(a semicolon followed by a space) and
a string C specifying the name of the company, returns a string containing the list of email addresses separated by the
characters "; " in the same order as they were in the input. Each entry on the list should be of the form 'Name <Email>".
For example, given C and string S as follows: "John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; John Elvis Doe;
John Evan Doe; Jane Doe; Peter Brian Parker" the function should return:
"John Doe <john.doe@example.com>; Peter Benjamin Parker <peter.parker@example.com>; Mary Jane Watson-Parker
<mary.watsonpa@example.com>; John Elvis Doe <john.doe2@example.com>; John Evan Doe <john.doe3@example.com>; Jane Doe
<jane.doe@example.com>; Peter Brian Parker <peter.parker2@example.com>".
Assume that
• N is an integer within the range [3..1,000];
• M is an integer within the range [1..100];
• string S consists only of letters (a -z and/or A-Z), spaces, hyphens and semicolons;
• string S contains valid names; no name appears more than once;
 */

class EmployeesEmailTest {
    @Test
    fun `test simple case`() {
        val employees = "John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; John Elvis Doe; " +
                "John Evan Doe; Jane Doe; Peter Brian Parker"
        val company = "example"

        val test = EmployeesEmails.solution(employees, company)

        val result = "John Doe <john.doe@example.com>; Peter Benjamin Parker <peter.parker@example.com>; " +
                "Mary Jane Watson-Parker <mary.watsonpa@example.com>; John Elvis Doe <john.doe2@example.com>; " +
                "John Evan Doe <john.doe3@example.com>; Jane Doe <jane.doe@example.com>; Peter Brian Parker <peter.parker2@example.com>"
        assertEquals(result, test)
    }

}

object EmployeesEmails {

    fun solution(S: String, C: String): String {
        val emails = mutableListOf<String>()

        val employees = S.split("; ")

        for (employee in employees) {
            val names = employee.split(" ")
            val first = names[0].toLowerCase()

            var lastname = names.last().toLowerCase().replace("-","")
            if (lastname.length > 8) {
                lastname = lastname.substring(0,8)
            }

            var email = "$first.$lastname"
            var count = 2
            var possibleAddress = email
            while (emails.contains(possibleAddress)) {
                possibleAddress = email + count
                count++
            }
            email = possibleAddress

            emails.add(email)
        }

        val baseFormat = "%s <%s@${C.toLowerCase()}.com>"

        val formattedAddresses = StringBuffer().apply {
            employees.forEachIndexed { index, emplName ->
                val format = if (index != employees.size - 1)
                    "$baseFormat; "
                else
                    baseFormat

                append(String.format(format, emplName, emails[index]))
            }
        }

        return formattedAddresses.toString()
    }

}



