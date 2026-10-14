import java.util.Scanner
class Menu( val keys: MutableList<Int>, val params: MutableList<String> ) {
    private val menuMap = mutableMapOf<Int, String>()
    fun inputCheckNum(input: String?): Boolean {
        val userInput: Int? = input?.toIntOrNull()
        if (userInput == null) { println("Введите корректное число.")
            return true
        }
        return false

    }
    fun inputCheckString(input: String?): Boolean{
        val userInputString: String? = input?.toString()
        if (!userInputString.isNullOrBlank()){
            return true
        }
        return false
    }
    fun showMenu(): List<String> {
        menuMap.clear()
        for (i in 0 until keys.size) {
            if (i < params.size) {
                menuMap[keys[i]] = params[i]
            }
        }
        return menuMap.map { (keys, params) -> "$keys  $params" } }
    fun addMenu(archiveName: Any) {
        val newKey = keys.size + 1
        keys.add(newKey)
        params.add("Архив: $archiveName") }
    fun getArchiveNameByKey(key: Int): String? {
        val index = keys.indexOf(key)
        if (index != -1 && index < params.size) {
            return params[index].removePrefix("Архив: ").trim() }
        return null
    }
}

    fun main(args: Array<String>) {
        val archive = Archive()
        val scanner = Scanner(System.`in`)

        val keys = mutableListOf(1, 2)
        val params = mutableListOf("Создать архив", "Выход")
        val menu = Menu(keys, params)

        println("Добро пожаловать в приложение для заметок!\n")

        while (true) {
            println("--- ГЛАВНОЕ МЕНЮ ---")
            val menuItems = menu.showMenu()
            menuItems.forEach { println(it) }

            print("Выберите пункт: ")

            var input = scanner.nextLine()
            while (menu.inputCheckNum(input)) {
                print("Попробуйте еще раз (введите число): ")
                input = scanner.nextLine()
            }

            val choice = input.toInt()

            when (choice) {
                0 -> {
                    println("До свидания!")
                    return
                }

                1 -> {
                    println("Создание нового архива")
                    print("Введите имя архива: ")
                    val archiveName = scanner.nextLine()
                    if (menu.inputCheckString(archiveName)) {
                        archive.createArchive(archiveName)
                        menu.addMenu(archiveName)
                        println("Архив '$archiveName' создан!\n")
                    } else {
                        println("Ошибка: имя архива не может быть пустым.\n")
                    }
                }

                2 -> {
                    println("До свидания!")
                    return
                }

                else -> {
                    val archiveName = menu.getArchiveNameByKey(choice)
                    if (archiveName != null && archive.hasArchive(archiveName)) {
                        while (true) {

                            val notes = archive.getNotes(archiveName) ?: mutableMapOf()

                            if (notes.isEmpty()) {

                                println("1. Добавить новую заметку")
                            } else {
                                println(" ------------------------- ")
                                println("Список заметок:")
                                var displayIndex: Int = 1
                                notes.forEach { (noteName, _) ->

                                    println("$displayIndex. $noteName")
                                    displayIndex++
                                }
                                println("$displayIndex. Добавить новую заметку")
                            }

                            println("0. Назад в главное меню")
                            print("Выберите действие: ")

                            var inputInner = scanner.nextLine()
                            while (menu.inputCheckNum(inputInner)) {
                                print("Попробуйте еще раз: ")
                                inputInner = scanner.nextLine()
                            }

                            val choiceInner = inputInner.toInt()
                            val totalNotes = notes.size
                            if (choiceInner == totalNotes + 1) {
                                println("---------------------------- ")
                                print("Имя заметки: ")
                                val noteName = scanner.nextLine()
                                print("Текст заметки: ")
                                val content = scanner.nextLine()
                                archive.addNote(archiveName, noteName, content)
                                println("Заметка '$noteName' добавлена!\n")

                            } else if (choiceInner in 1..totalNotes) {
                                val selectedNoteName = notes.keys.toList()[choiceInner - 1]
                                println(" ---------------------------")
                                println("Вы выбрали заметку: $selectedNoteName")

                                val content = notes[selectedNoteName]
                                println("Текст: $content")
                                }
                            else if (choiceInner == 0) {
                                    break
                                }
                            else {
                                    println("Неверный пункт или архив удален.\n")
                                    }
                                }
                            }
                        }
                    }
                }
            }






