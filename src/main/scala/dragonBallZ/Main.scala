package dragonBallZ

import dragonBallZ.model._

object Main {

  def printlnWhite(txt: String): Unit = {
    val ANSI_RED = "\u001B[31m"
    val ANSI_RESET = "\u001B[0m"
    println(ANSI_RED + txt + ANSI_RESET)
  }

  def main(args: Array[String]) {

    val patadaAlta = Movement("Patada alta", 15, 20)
    val patadaBaja = Movement("Patada baja", 5, 3)
    val kamehameha = Movement("Kamehameha", 500, 400)
    val finalFlash = Movement("Final Flash", 400, 350)
    val makakosanpo = Movement("Makakosanpo", 1000, 700)

    val buenos: Team = Team("Los Buenos",
      List(
        Warrior("Krillin", 300, 50, List(patadaAlta)),
        Warrior("Goku", 1500, 300, List(kamehameha)))
    )

    val detenerFreezer: Mision = Mision("Detener a Freezer", 250,
      List(
        Activity("Distraer", 50),
        Activity("Pegar", 100),
        Activity("Matar", 300))
    )

    ///////////***************////////

    val dormir: Mision = Mision("Dormir", 300,
      List(
        Activity("Tomar te", 25),
        Activity("Acostarse", 10),
        Activity("Apagar la luz", 45))
    ) //105
    val comer: Mision = Mision("Comer", 150,
      List(
        Activity("Pedir el rappi", 5),
        Activity("Comer", 10),
        Activity("Limpiar", 45))
    ) //20

    val jovenes: Team = Team("Jovenes Adultos",
      List(
        Warrior("Cami", 740, 100, List(patadaAlta, makakosanpo)),
        Warrior("Gaston", 900, 15, List(finalFlash, patadaBaja)),
        Warrior("Pablis", 1200, 200, List(patadaAlta, makakosanpo)),
        Warrior("Gabi", 1000, 45,List(kamehameha, patadaBaja)))
    )
    val sub21: Team = Team("Sub21",
      List(
        Warrior("Chino", 700, 20, List(patadaAlta, kamehameha)),
        Warrior("Facu", 650, 45, List(patadaAlta, kamehameha)),
        Warrior("Jose", 880, 75, List(finalFlash, makakosanpo)))
    )

    val misionHandler: HandleMisionService = HandleMisionService(List(dormir, comer))

    printlnWhite("ApplyTeamAction: "+ misionHandler.applyTeamAction(jovenes))
    println()
    //println("Warriors Left " + jovenes.name + ": " +  misionHandler.fight(Some(jovenes),sub21)+ "\n")
    //println("Warriors Left " + sub21.name + ": " + misionHandler.fight(Some(sub21),jovenes)+ "\n\n")
    printlnWhite("Better Team For The Mision: " + misionHandler.betterTeamForTheMision(buenos, sub21))
  }
}