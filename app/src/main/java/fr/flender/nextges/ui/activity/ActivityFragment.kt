package fr.flender.nextges.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.flender.nextges.R
import fr.flender.nextges.databinding.FragmentActivityBinding
import fr.flender.nextges.models.Class
import fr.flender.nextges.models.Event
import fr.flender.nextges.repositories.ClassRepository
import fr.flender.nextges.repositories.EventRepository
import fr.flender.nextges.repositories.source.firestore.FirestoreClassSource
import fr.flender.nextges.repositories.source.firestore.FirestoreEventSource
import fr.flender.nextges.ui.adapter.ClassAdapter
import fr.flender.nextges.ui.adapter.EventAdapter
import fr.flender.nextges.ui.planning.PlanningViewModel
import fr.flender.nextges.ui.planning.PlanningViewModelFactory

class ActivityFragment: Fragment() {

    private lateinit var adapterEvent: EventAdapter
    private lateinit var activityViewModel: ActivityViewModel

    private var _binding: FragmentActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val firestoreDataSource = FirestoreEventSource()
        val repository = EventRepository(firestoreDataSource);
        val factory = ActivityViewModelFactory(repository)

        activityViewModel =
            ViewModelProvider(this, factory).get(ActivityViewModel::class.java)

        _binding = FragmentActivityBinding.inflate(inflater, container, false)
        val root: View = binding.root

        activityViewModel.fetchData()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterEvent = EventAdapter()
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.event_recycleView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterEvent
        }

        activityViewModel.events.observe(viewLifecycleOwner) { events ->
            updateRecyclerViewEvents(events)
        }
    }

    private fun updateRecyclerViewEvents(events: List<Event>) {
        adapterEvent.submitList(events)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}