fun main(args: Array<String>) {
    println("Entrez votre texte :")
    /*Réaliser un programme qui permet de détecter si un texte est un palindrome.
    NB: L'algorithme doit se baser sur l'utilisation de boucle et de collection.*/
    val text: String = readLine()!!

    //formattage du texte
    var formattedText = text.lowercase().trim()
    formattedText = removeDiacritic(formattedText).toString()
    formattedText = formattedText.removeNonText().toString()

    val maxIndex: Int = formattedText.length - 1
    val textLength: Int = formattedText.length/2
    var isPalind = true
    var i:Int = 0
    println(formattedText.toString())
    while (i< textLength && isPalind) {
        val charAlfa = formattedText[i]
        val charOmega = formattedText[maxIndex-i]
        if (charAlfa != charOmega) {
            isPalind = false
        }
        i++
    }
     println("Le texte ${if (isPalind) "est" else "n'est pas"} un palindrome.")
}

private fun removeDiacritic(s : String): String {
    val tempo = s.replace("é","e",true)
        .replace("è","e",true)
        .replace("ê","e",true)
        .replace("ë","e",true)
        .replace("à","a",true)
        .replace("â","a",true)
        .replace("î","i",true)
        .replace("ï","i",true)
        .replace("ô","o",true)
        .replace("ù","u",true)
        .replace("û","u",true)
    return tempo
}
private fun String.removeNonText(): String {
    val tempo = this.replace(" ","",true)
                    .replace(".","",true)
                    .replace(",","",true)
                    .replace("'","",true)
                    .replace("\"","",true)
                    .replace("!","",true)
                    .replace("?","",true)
                    .replace(";","",true)
    return tempo
}
