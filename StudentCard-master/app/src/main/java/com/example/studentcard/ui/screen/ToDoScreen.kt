package com.example.studentcard.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.studentcard.database.controller.ToDoController
import com.example.studentcard.database.dataclass.ToDo
import androidx.compose.foundation.lazy.items

/**
 * The Method implements the ToDoScreen.
 */
@Composable
fun ToDoScreen(toDoController: ToDoController) {
    var todos by remember { mutableStateOf(toDoController.getAllToDos(1)) }
    var isAdding by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    var editingToDo by remember { mutableStateOf<ToDo?>(null) }
    var context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        Text("ToDo List")
        Spacer(modifier = Modifier.height(16.dp))

        if (isAdding || isEditing) {
            ToDoForm(
                initialToDo = editingToDo,
                onSave = { todo ->
                    if (isAdding) {
                        if (toDoController.insertToDo(todo)) {
                            todos = toDoController.getAllToDos(1)
                            isAdding = false
                        } else {
                            Toast.makeText(context, "Error saving ToDo", Toast.LENGTH_SHORT).show()
                        }
                    } else if (isEditing && editingToDo != null) {
                        if (toDoController.updateToDo(todo)) {
                            todos = toDoController.getAllToDos(1)
                            isEditing = false
                            editingToDo = null
                        } else {
                            Toast.makeText(context, "Error updating ToDo", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                onCancel = {
                    isAdding = false
                    isEditing = false
                    editingToDo = null
                }
            )
        } else {
            Button(onClick = { isAdding = true }) {
                Text("+")
            }

            Spacer(modifier = Modifier.height(16.dp))

            ToDoList(todos, onDelete = { id ->
                if (toDoController.deleteToDo(id)) {
                    todos = toDoController.getAllToDos(1)
                } else {
                    Toast.makeText(context, "Error deleting ToDo", Toast.LENGTH_SHORT).show()
                }
            }, onEdit = { todo ->
                editingToDo = todo
                isEditing = true
            })
        }
    }
}

/**
 * This Method manages the creation and update of new ToDos.
 */
@Composable
fun ToDoForm(
    initialToDo: ToDo?,
    onSave: (ToDo) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue(initialToDo?.name ?: "")) }
    var description by remember { mutableStateOf(TextFieldValue(initialToDo?.description ?: "")) }
    var priority by remember { mutableStateOf(TextFieldValue(initialToDo?.priority ?: "Medium")) }
    var deadline by remember { mutableStateOf(TextFieldValue(initialToDo?.deadline ?: "2025-01-01")) }

    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = priority,
            onValueChange = { priority = it },
            label = { Text("Priority") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = deadline,
            onValueChange = { deadline = it },
            label = { Text("Deadline") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { onCancel() }) {
                Text("Cancel")
            }

            Button(onClick = {
                onSave(
                    ToDo(
                        id = initialToDo?.id ?: 0,
                        name = name.text,
                        description = description.text,
                        priority = priority.text,
                        deadline = deadline.text,
                        status = 1
                    )
                )
            }) {
                Text(if (initialToDo != null) "Update" else "Save")
            }
        }
    }
}

/**
 * The Method to display the ToDoList.
 */
@Composable
fun ToDoList(todos: List<ToDo>, onDelete: (Int) -> Unit, onEdit: (ToDo) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(todos) { todo ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Name: ${todo.name}")
                    Text("Description: ${todo.description}")
                    Text("Priority: ${todo.priority}")
                    Text("Deadline: ${todo.deadline}")

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Button(onClick = {
                            todo.id?.let { id -> onDelete(id) }
                                ?: Log.e("ToDoScreen", "Delete failed: ToDo ID is null")
                        }) {
                            Text("Delete")
                        }

                        Button(onClick = { onEdit(todo) }) {
                            Text("Edit")
                        }
                    }
                }
            }
        }
    }
}
