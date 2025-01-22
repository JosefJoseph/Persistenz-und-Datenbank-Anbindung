package com.example.studentcard.database.controller

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.studentcard.database.DbHelper
import com.example.studentcard.database.dataclass.ToDo

/**
 * The Class manages the ToDo and CRUD-Operations.
 */
class ToDoController(context: Context) {
    private val dbHelper = DbHelper(context)

    /**
     * This Method inserts a ToDo into the Database.
     */
    fun insertToDo(todo: ToDo): Boolean {
        val db = dbHelper.writableDatabase
        return try {
            val values = ContentValues().apply {
                put("name", todo.name)
                put("priority", todo.priority)
                put("deadline", todo.deadline)
                put("description", todo.description)
                put("status", todo.status)
            }
            val result = db.insert("ToDo", null, values)
            result != -1L
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            db.close()
        }
    }

    /**
     * This Method updates a ToDo in the Database.
     */
    fun updateToDo(todo: ToDo): Boolean {
        val db = dbHelper.writableDatabase
        return try {
            val values = ContentValues().apply {
                put("name", todo.name)
                put("priority", todo.priority)
                put("deadline", todo.deadline)
                put("description", todo.description)
                put("status", todo.status)
            }
            val result = db.update("Todo", values, "id = ?", arrayOf(todo.id.toString()))
            Log.d("TodoController", "Update result: $result, ToDo: ${todo.id}")
            result > 0
        } catch (e: Exception) {
            Log.e("TodoController", "Update failed", e)
            false
        } finally {
            db.close()
        }
    }

    /**
     * This Method deletes a ToDo from the Database.
     */
    fun deleteToDo(id: Int): Boolean {
        Log.d("TodoController", "Attempting to delete ToDo with ID: $id")
        val db = dbHelper.writableDatabase
        return try {
            db.delete("ToDo", "id = ?", arrayOf(id.toString())) > 0
        } catch (e: Exception) {
            Log.e("TodoController", "Delete failed", e)
            false
        } finally {
            db.close()
        }
    }

    /**
     * This Method fetches all ToDos from the Database.
     */
    fun getAllToDos(status: Int): List<ToDo> {
        val db = dbHelper.readableDatabase
        val todos = mutableListOf<ToDo>()
        val cursor = db.rawQuery("SELECT * FROM ToDo WHERE status = ?", arrayOf(status.toString()))
        try {
            if (cursor.moveToFirst()) {
                do {
                    val todo = ToDo(
                        id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        description = cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        priority = cursor.getString(cursor.getColumnIndexOrThrow("priority")),
                        deadline = cursor.getString(cursor.getColumnIndexOrThrow("deadline")),
                        status = cursor.getInt(cursor.getColumnIndexOrThrow("status"))
                    )
                    todos.add(todo)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.e("ToDoController", "Fetching ToDos failed", e)
        } finally {
            cursor.close()
            db.close()
        }
        return todos
    }

}