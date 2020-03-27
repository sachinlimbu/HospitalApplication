package com.org.hospitalapplication.viewmodels
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.org.hospitalapplication.data.model.HospitalData
import com.org.hospitalapplication.data.network.Repository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HospitalViewModelTest{

    private lateinit var hospitalViewModel: HospitalViewModel

    @Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repositoryMock: Repository

    private lateinit var viewModel: HospitalViewModel

    @Mock
    private lateinit var repositoryHospital: Observer<List<HospitalData>>

    @Before
    fun setup(){
        viewModel.mHospitalloadingState.observeForever(repositoryHospital)
    }

    @Test

    fun testSetupHospital(){
        `when`(repositoryMock.getData())


    }



}