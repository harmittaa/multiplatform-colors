import com.harmittaa.multiplatformcolors.di.commonModule
import com.harmittaa.multiplatformcolors.di.networkModule
import com.harmittaa.multiplatformcolors.viewmodel.viewModelModule

fun appModule() = listOf(
    commonModule,
    networkModule,
    viewModelModule,
)
