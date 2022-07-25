package com.example.cardgamescore.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.cardgamescore.BR
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragment<BINDING : ViewDataBinding, VM : BaseViewModel>(
    clazz: KClass<VM>
) : Fragment() {
    lateinit var binding: BINDING

    protected open val viewModel: VM by viewModel(clazz)

    abstract fun getLayoutId(): Int

    protected open fun getBindingVariable(): Int = BR.viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contentViewId = getLayoutId()
        binding = DataBindingUtil.inflate(inflater, contentViewId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(getBindingVariable(), viewModel)
        binding.executePendingBindings()
        binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewReady()
        observedData()
    }

    abstract fun onViewReady()

    abstract fun observedData()

}