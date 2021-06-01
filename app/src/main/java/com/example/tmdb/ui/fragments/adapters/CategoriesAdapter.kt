package com.example.tmdb.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.data.discover.entities.bo.Movie
import com.example.tmdb.databinding.ItemCategoryBinding
import com.example.tmdb.ui.fragments.Category
import com.example.tmdb.utils.BindAdapter

// TODO: 6/1/21 generise this adapter
class CategoriesAdapter(
    private var mItems: List<Category<Movie>> = ArrayList()
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>(),
    BindAdapter<List<Category<Movie>>> {

    override fun setData(data: List<Category<Movie>>) {
        this.mItems = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        // TODO: 6/1/21 add support to different types of holders
        val dataBinding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = mItems[position]

        holder.bind(item)
    }

    override fun getItemCount() = mItems.size

    class CategoryViewHolder(
        private val mBinding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(category: Category<Movie>) {
            mBinding.apply {
                setVariable(BR.category, category)
                executePendingBindings()
            }
            mBinding.categoryContentRecyclerView.adapter = MoviesAdapter(category.mItems)
        }
    }

}