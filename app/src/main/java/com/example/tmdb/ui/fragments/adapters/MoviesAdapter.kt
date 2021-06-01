package com.example.tmdb.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.data.discover.entities.bo.Movie
import com.example.tmdb.databinding.ItemMovieBinding
import com.example.tmdb.utils.BindAdapter

// TODO: 6/1/21 generise this adapter
class MoviesAdapter(
    private var mItems: List<Movie> = ArrayList()
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>(),
    BindAdapter<List<Movie>> {

    override fun setData(data: List<Movie>) {
        this.mItems = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        // TODO: 6/1/21 add support to different types of holders
        val dataBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = mItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = mItems.size

    class MovieViewHolder(private val mBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bind(movie: Movie) {
            mBinding.apply {
                setVariable(BR.movie, movie)
                executePendingBindings()
            }
        }
    }
}