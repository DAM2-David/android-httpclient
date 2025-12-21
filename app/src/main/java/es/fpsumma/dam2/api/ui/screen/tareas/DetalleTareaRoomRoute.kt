package es.fpsumma.dam2.api.ui.screen.tareas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import es.fpsumma.dam2.api.viewmodel.Tarea
import es.fpsumma.dam2.api.viewmodel.TareasViewModel

@Composable
fun DetalleTareaRoomRoute(
    id: Int,
    navController: NavController,
    vm: TareasViewModel
) {
    val tareaFlow = remember(id) { vm.getTarea(id) }
    val tareaEntity by tareaFlow.collectAsStateWithLifecycle(initialValue = null)
    val tareaUI = tareaEntity?.let {
        Tarea(it.id, it.titulo, it.descripcion)
    }

    DetalleTareaContent(
        tarea = tareaUI,
        onBack = { navController.popBackStack() },
        onUpdate = { titulo, descripcion ->
            vm.updateTarea(id, titulo, descripcion)
            navController.popBackStack()
        }
    )
}