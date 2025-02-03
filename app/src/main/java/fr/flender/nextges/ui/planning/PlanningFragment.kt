package fr.flender.nextges.ui.planning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.flender.nextges.R
import fr.flender.nextges.databinding.FragmentPlanningBinding
import fr.flender.nextges.models.Class
import fr.flender.nextges.repositories.ClassRepository
import fr.flender.nextges.repositories.source.firestore.FirestoreClassSource
import fr.flender.nextges.ui.adapter.ClassAdapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class PlanningFragment: Fragment() {

    private lateinit var adapterClass: ClassAdapter

    private lateinit var planningViewModel: PlanningViewModel

    private var date: Date = Date()

    private var _binding: FragmentPlanningBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val firestoreDataSource = FirestoreClassSource()
        val repository = ClassRepository(firestoreDataSource);
        val factory = PlanningViewModelFactory(repository)

        planningViewModel =
            ViewModelProvider(this, factory).get(PlanningViewModel::class.java)

        _binding = FragmentPlanningBinding.inflate(inflater, container, false)
        val root: View = binding.root

        planningViewModel.fetchData(date)

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterClass = ClassAdapter()
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.class_recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterClass
        }

        planningViewModel.classes.observe(viewLifecycleOwner) { classes ->
            updateRecyclerViewClass(classes)
        }

        var btnPrevious = view.findViewById<Button>(R.id.class_btn_prev);
        var btnCurrent = view.findViewById<Button>(R.id.class_btn_current);
        var btnNext = view.findViewById<Button>(R.id.class_btn_next);

        btnPrevious.setOnClickListener(createButtonClickListener())
        btnCurrent.setOnClickListener(createButtonClickListener())
        btnNext.setOnClickListener(createButtonClickListener())

    }

    private fun createButtonClickListener(): View.OnClickListener {
        return View.OnClickListener { button ->
            date = when (button.id) {
                R.id.class_btn_prev -> {
                    Calendar.getInstance().apply {
                        time = date
                        add(Calendar.DAY_OF_YEAR, -1)
                    }.time
                }

                R.id.class_btn_next -> {
                    Calendar.getInstance().apply {
                        time = date
                        add(Calendar.DAY_OF_YEAR, 1)
                    }.time
                }

                else -> Date()
            }
            planningViewModel.fetchData(date)

            val formatter = SimpleDateFormat("EEEE d MMMM yyyy", Locale.getDefault())
            val formattedDate = formatter.format(date)
            requireView().findViewById<TextView>(R.id.class_current_day_text).text = formattedDate
        }
    }

    private fun updateRecyclerViewClass(classes: List<Class>) {
        adapterClass.submitList(classes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}