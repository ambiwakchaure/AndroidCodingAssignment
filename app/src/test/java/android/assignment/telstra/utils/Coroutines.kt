package android.assignment.telstra.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines
{
    fun io(work: suspend (() -> Unit)) = //unit same as java void
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }
}