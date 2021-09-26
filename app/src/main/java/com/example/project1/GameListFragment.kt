package com.example.project1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

private const val TAG = "GameListFragment"

class GameListFragment : Fragment() {
    /**
     * Required interface for hosting activities */
    interface Callbacks {
        fun onGameSelected(gameId: UUID)
    }
    private var callbacks: Callbacks? = null

    private lateinit var gameRecyclerView: RecyclerView
    private var adapter: GameAdapter? = GameAdapter(emptyList())

//    private val bbViewModel: BBViewModel by lazy {
//        ViewModelProviders.of(this).get(BBViewModel::class.java)
//    }

    private val bbViewModel: BBViewModel by activityViewModels()

    companion object {
        fun newInstance(): GameListFragment {
            return GameListFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        gameRecyclerView =
            view.findViewById(R.id.game_recycler_view) as RecyclerView
        gameRecyclerView.layoutManager = LinearLayoutManager(context)

        gameRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bbViewModel.gameListLiveData.observe(
            viewLifecycleOwner,
            Observer { games ->
                games?.let {
                    Log.i(TAG, "Got games ${games.size}")
                    updateUI(games)
                }
        })
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun updateUI(games: List<Game>) {
        adapter = GameAdapter(games)
        gameRecyclerView.adapter = adapter
    }

    private inner class GameHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var game: Game

        private val numberTextView: TextView = itemView.findViewById(R.id.game_number)
        private val dateTextView: TextView = itemView.findViewById(R.id.game_date)
        private val teamsTextView: TextView = itemView.findViewById(R.id.game_teams)
        private val scoresTextView: TextView = itemView.findViewById(R.id.game_scores)
        private val iconImageView: ImageView = itemView.findViewById(R.id.game_icon)

        fun bind(game: Game) {
            this.game = game
            numberTextView.text = this.game.title
            dateTextView.text = this.game.date.toString()
            teamsTextView.text = this.game.teamA.name.toString().plus(" : ").plus(this.game.teamB.name.toString())
            scoresTextView.text = this.game.teamA.score.toString().plus(" : ").plus(this.game.teamB.score.toString())
            if(this.game.teamA.isWinner){
                iconImageView.setImageResource(R.drawable.parrot)
            } else if(this.game.teamB.isWinner){
                iconImageView.setImageResource(R.drawable.pirate)
            }
        }

        override fun onClick(v: View?) {
            callbacks?.onGameSelected(game.id) }
    }

    private inner class GameAdapter(var games: List<Game>)
        : RecyclerView.Adapter<GameHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : GameHolder {
            val view = layoutInflater.inflate(R.layout.list_item_game, parent, false)
            return GameHolder(view)
        }

        override fun getItemCount() = games.size

        override fun onBindViewHolder(holder: GameHolder, position: Int) {
            val game = games[position]
            holder.bind(game)
        }
    }

}