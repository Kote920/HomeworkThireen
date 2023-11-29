package com.example.homeworkthireen

import android.R
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkthireen.databinding.ItemButtonBinding
import com.example.homeworkthireen.databinding.ItemChoiceBinding
import com.example.homeworkthireen.databinding.ItemInputBinding


class FieldRecyclerAdapter() :
    ListAdapter<Field, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Field>() {
        override fun areItemsTheSame(oldItem: Field, newItem: Field): Boolean {
            return oldItem.field_id == newItem.field_id

        }

        override fun areContentsTheSame(oldItem: Field, newItem: Field): Boolean {
            return oldItem == newItem
        }

    }) {


    private var editTextValues: List<String>? = null
//    private lateinit var userName: String
//    private lateinit var email: String
//    private lateinit var phone: String
//    private lateinit var fullName: String
//    private lateinit var jemali: String
//    private lateinit var birthday: String
//    private lateinit var gender: String
    private val person = Person("f")
    private var users = mutableListOf<Person>()

    companion object {
        const val INPUT_TYPE = 1
        const val CHOICE_TYPE = 2
        const val BUTTON_TYPE = 3

    }


    fun setData(fields: MutableList<Field>) {

        submitList(fields)


    }

    inner class InputTypeViewHolder(private val binding: ItemInputBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind() {
            binding.etInput.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    if(binding.etInput.hint == "UserName")
                        person.name = binding.etInput.toString()
                }
            }

            val field = currentList[adapterPosition]
            with(binding) {
                etInput.hint = field.hint
            }
        }

    }

    inner class ChoiceTypeViewHolder(private val binding: ItemChoiceBinding) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind() {
            binding.etChoice.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    if(binding.etChoice.hint == "UserName")
                    person.name = binding.etChoice.toString()
                }
            }
            val field = currentList[adapterPosition]
            with(binding) {
                etChoice.hint = field.hint

            }

        }
    }

    lateinit var  itemOnClick: ()-> MutableList<Person>
    inner class ButtonTypeViewHolder(private val binding: ItemButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private fun listeners(){
            binding.root.setOnClickListener {
                users.add(person)
            }
        }
    }



    public override fun getItem(position: Int): Field {
        return super.getItem(position)
    }
    override fun getItemViewType(position: Int): Int {
        if (currentList[position].field_type == "input") {
            return INPUT_TYPE
        } else if(currentList[position].field_type == "chooser"){
            return CHOICE_TYPE

        }else {
            return BUTTON_TYPE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == INPUT_TYPE) {
            return InputTypeViewHolder(
                ItemInputBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else if (viewType == CHOICE_TYPE){
            return ChoiceTypeViewHolder(
                ItemChoiceBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        }else{
            return ButtonTypeViewHolder(
                ItemButtonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is InputTypeViewHolder) {
            holder.bind()
        } else if (holder is ChoiceTypeViewHolder) {
            holder.bind()

        }
    }
}