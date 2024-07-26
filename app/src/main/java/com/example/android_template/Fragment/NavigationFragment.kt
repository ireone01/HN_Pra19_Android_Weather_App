package com.example.android_template.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_template.DailyActivity
import com.example.android_template.HourlyActivity
import com.example.android_template.R
import com.example.android_template.RadaActivity
import com.example.android_template.databinding.FragmentNavigationBinding

class NavigationFragment : Fragment() {
    private lateinit var binding: FragmentNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToday.setOnClickListener {
            navigateToFragment(HomeFragment())
        }
        binding.btnHourly.setOnClickListener {
            navigateToFragment(HourlyActivity())
        }
        binding.btnDaily.setOnClickListener {
            navigateToFragment(DailyActivity())
        }
        binding.btnRadar.setOnClickListener {
            navigateToFragment(RadaActivity())
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

        parentFragmentManager.beginTransaction()
            .replace(R.id.navigation,NavigationFragment())
            .addToBackStack(null)
            .commit()
    }
}
