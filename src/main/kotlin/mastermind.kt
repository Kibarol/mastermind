import java.util.*

fun main() {
    do {
        println("Choisissez un niveau de difficulté :\n 1 - 4 chiffres / 20 tentatives max\n 2 - 4 chiffres / 12 tentatives max\n 3 - 5 chiffres / 10 tentatives max ")
        val difficulte = readLine()?.toInt()
        var longueur: Int = when (difficulte) {
            1 -> 4
            2 -> 4
            3 -> 5
            else -> 0
        }
        val codeMyst: Array<Int> = genererCodeMystere(longueur)
        println(codeMyst.contentToString())

        var nbEssais = when (difficulte) {
            1 -> 20
            2 -> 12
            else -> 10
        }
        var trouve = false
        do {
            if (nbEssais == 1) println("!!! Dernière tentative !!!")
            val codeJoueur: Array<Int> = proposeTonCode(longueur)
            println(codeJoueur.contentToString())
            trouve = sontIlsIdentiques(codeJoueur, codeMyst, longueur)
            comparerLesCodes(codeJoueur, codeMyst, longueur)
            nbEssais--
        } while (nbEssais > 0 && !trouve)
        if (difficulte != null) {
            println("Votre score : ${scoreFinal(trouve, nbEssais, longueur, difficulte)}.")
        }
        println("Voulez-vous rejouer ? o/n")
        val input = readLine()!!.lowercase()
        val rejouer = when(input){
            "n" -> false
            else -> true
        }
    } while (rejouer)
}

fun scoreFinal(trouve: Boolean, nbEssais: Int, longueur: Int, difficulte: Int): Int {
    var score = 0
    if(trouve) score = difficulte*(100 + nbEssais*10)
    return score
}


fun genererCodeMystere(longueur:Int): Array<Int> {
    val codeMystere: Array<Int> = arrayOf(0,0,0,0,0)
    for (i:Int in 0 until longueur){
       codeMystere[i]  = (0..9).random()
    }

    return codeMystere
}
//Tentatives du joueur
fun proposeTonCode(longueur:Int):Array<Int> {
    var index: Int = 0
    val codeJoueur: Array<Int> = arrayOf(0,0,0,0,0)
    for (index in 0 until longueur) {
        var invalid: Boolean = true
        var input: String
        do {
            println("Proposer le nombre ${(index + 1)}")
            input = readLine().toString()
            if (input != null) {
                if (input.toInt() in 0..9) {
                    invalid = false
                }
            }
        } while (invalid)
        if (input != null) {
            codeJoueur[index] = input.toInt()
        }
    }
    return codeJoueur
}
fun comparerLesCodes(a1: Array<Int> , a2: Array<Int>, longueur:Int ): Unit {
    //comparatifs
    var nbCorrects: Int = 0
    var nbPresents: Int = 0
    var indexJ: Int = 0
    var indexM: Int = 0
    var finBoucle = longueur
    for (indexJ in 0 until finBoucle){
        for (indexM in 0 until finBoucle){
            if((a1[indexJ] == a2[indexM]) && (indexJ == indexM)) {
                nbCorrects++
                a1[indexJ] = a1[longueur-1]
                a2[indexM] = a2[longueur-1]
                finBoucle--
            }
        }
    }
    for (indexJ in 0 until finBoucle){
        for (indexM in 0 until finBoucle){
            if (a1[indexJ] == a2[indexM]) nbPresents++
        }
    }
    print(if (nbCorrects >0) "Il y a $nbCorrects élément(s) bien placé(s) " else "Il n'y a aucun élément bien placé ")
    println(if (nbPresents >0) "et $nbPresents élément(s) mal placé(s) " else "et aucun élément mal placé ")
}
fun sontIlsIdentiques(a1: Array<Int> , a2: Array<Int>, longueur:Int ): Boolean {
    var identiques = true
    for (i:Int in 0 until longueur){
        if (a1[i]!=a2[i]) {identiques = false
            break}
    }
    return identiques
}
