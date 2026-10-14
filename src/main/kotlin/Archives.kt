
class Archive { // Структура: Имя архива -> (Имя заметки -> Текст заметки)
     val archiveMap = mutableMapOf<String, MutableMap<String, String>>()
    fun createArchive(name: Any) {
    if (!archiveMap.containsKey(name)) {
        archiveMap[name.toString()] = mutableMapOf()

        } else { println("Архив 'name' уже существует.")
    }
}
    fun addNote(archiveName: String, noteName: String, content: String) {
    if (archiveMap.containsKey(archiveName)) {
        archiveMap[archiveName]?.put(noteName, content)
         } else { println("Ошибка: архив не найден!")
        }
    }
    fun getNotes(archiveName: String): MutableMap<String, String>? {
    return archiveMap[archiveName]
    }
    fun hasArchive(name: String) = archiveMap.containsKey(name)
    }




