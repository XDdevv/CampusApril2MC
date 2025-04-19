package zed.rainxch.april2minichallenge.data

object FactData {
    private fun getFactList() = listOf(
        "The tradition of decorating eggs dates back to ancient civilizations, including Egyptians and Persians.",
        "The largest Easter egg ever made was over 7 meters tall and weighed over 3,600 kilograms.",
        "In some cultures, eggs are painted red to symbolize the blood of Christ.",
        "The Easter Bunny originated from German folklore, where it was believed to lay eggs for children to find.",
        "In 1970, the first-ever Easter egg hunt was held on the White House lawn.",
        "Cadbury's Crème Egg is one of the most popular Easter treats, with over 200 million sold each year.",
        "In Finland, Easter is celebrated with a tradition where children dress up as witches and go door-to-door for treats.",
        "The word \"Easter\" is believed to be derived from \"Eostre,\" a pagan goddess of spring and fertility.",
        "In Ukraine, the art of decorating eggs is known as \"Pysanky,\" which involves intricate designs using wax and dye.",
        "The tradition of the Easter egg hunt is thought to symbolize the search for the tomb of Jesus.",
        "In Greece, red eggs are cracked against each other in a game called \"tsougrisma,\" symbolizing the resurrection.",
        "The practice of giving chocolate eggs started in the 19th century in Europe.",
        "The world's most expensive chocolate egg, made by a luxury chocolatier, was valued at over €144,000.",
        "In some cultures, Easter eggs are hidden in gardens and parks for children to find, promoting outdoor activity."
    )

    fun getRandomFact() = getFactList().random()
}