package com.org.hospitalapplication.viewmodels
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.org.hospitalapplication.data.model.HospitalData
import com.org.hospitalapplication.data.network.Repository
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class HospitalViewModelTest{

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repositoryMock: Repository

    private lateinit var viewModel: HospitalViewModel

    @Before
    fun setup(){
        viewModel = HospitalViewModel(repositoryMock)
    }

    @Test
    fun whenRepositoryReturnsEmptyList_showError(){
        `when`(repositoryMock.getData()).thenReturn(Single.just(listOf()))
        viewModel.getHospitalData()
        assertEquals(
            HospitalViewModel.LoadingHospitalState.ERROR("No Hospital found"),
            viewModel._mHospitalLoadingState.value
        )
    }

    @Test
    fun whenRepositoryReturnsNonEmptyList_showSuccess(){
        val data = listOf(HospitalData(1))
        `when`(repositoryMock.getData()).thenReturn(Single.just(data))
        viewModel.getHospitalData()
        assertEquals(
            HospitalViewModel.LoadingHospitalState.SUCCESS(data),
            viewModel._mHospitalLoadingState.value
        )
    }

    @Test
    fun whenRepositoryReturnsExceptionWithMessage_showErrorWithSameMessage(){
        val errorMessage = "Demo Error"
        `when`(repositoryMock.getData()).thenReturn(Single.error(RuntimeException(errorMessage)))
        viewModel.getHospitalData()
        assertEquals(
            HospitalViewModel.LoadingHospitalState.ERROR(errorMessage),
            viewModel._mHospitalLoadingState.value
        )
    }


    @Test
    fun whenRepositoryReturnsExceptionWithoutMessage_showErrorWithGenericMessage(){
        `when`(repositoryMock.getData()).thenReturn(Single.error(RuntimeException()))
        viewModel.getHospitalData()
        assertEquals(
            HospitalViewModel.LoadingHospitalState.ERROR("Some error occurred"),
            viewModel._mHospitalLoadingState.value
        )
    }
}