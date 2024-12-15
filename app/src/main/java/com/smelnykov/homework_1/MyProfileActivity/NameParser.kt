package com.smelnykov.homework_1.MyProfileActivity

/**
 * Name and surname parser object.
 */
class NameParser {

    /**
     * Method for parsing name and surname from email.
     * name and surname will be parse form email type: name.surname@...
     *
     * @param email email with name and surname.
     * @return String which contains name and surname.
     */
    fun parseNameSurname(email: String): String {

        val nameSurname = email.substringBefore("@")
        if (!nameSurname.contains('.')) {
            return nameSurname.replaceFirstChar ( Char::uppercase )
        }

        val name = nameSurname.substringBefore(".")
        val surname = nameSurname.substringAfter(".")

        return "${name.replaceFirstChar(Char::uppercase)} ${surname.replaceFirstChar(Char::uppercase)}"
    }
}