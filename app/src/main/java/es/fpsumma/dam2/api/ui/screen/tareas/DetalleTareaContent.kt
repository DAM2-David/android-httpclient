package es.fpsumma.dam2.api.ui.screen.tareas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.fpsumma.dam2.api.viewmodel.Tarea

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetalleTareaContent(
    tarea: Tarea?,
    onBack: () -> Unit,
    onUpdate: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var titulo by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(tarea) {
        tarea?.let {
            titulo = it.titulo
            descripcion = it.descripcion
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle Tarea") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->

        if (tarea == null) {
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
                Text("Cargando tarea...", modifier = Modifier.padding(top = 40.dp))
            }
        } else {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    singleLine = true,
                    modifier = modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") },
                    singleLine = false,
                    modifier = modifier.fillMaxWidth()
                )
                Button(
                    onClick = { onUpdate(titulo, descripcion) },
                    modifier = modifier.fillMaxWidth()
                ) { Text("Actualizar Tarea") }
            }
        }
    }
}