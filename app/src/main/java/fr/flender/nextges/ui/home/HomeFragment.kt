package fr.flender.nextges.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fr.flender.nextges.R
import fr.flender.nextges.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var btnPlanning = view.findViewById<Button>(R.id.home_btn_planning)
        var btnEvents = view.findViewById<Button>(R.id.home_btn_events)
        var btnProfile = view.findViewById<Button>(R.id.home_btn_profile)

        val navController = findNavController()

        btnPlanning.setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_nav_planning)
        }

        btnEvents.setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_nav_activity)
        }

        btnProfile.setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_nav_profile)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}