package com.example.kinoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.kinoapp.favorites.FavoritesFragment
import com.example.kinoapp.movies.MoviesFragment
import com.example.kinoapp.genres.GenresFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(MoviesFragment())
        adapter.addFragment(GenresFragment())
        adapter.addFragment(FavoritesFragment())

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.first)
                1 -> tab.text = getString(R.string.second)
                2 -> tab.text = getString(R.string.third)
            }
        }.attach()

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_movies -> {
                    viewPager.currentItem = 0
                    true
                }
                R.id.menu_genres -> {
                    viewPager.currentItem = 1
                    true
                }
                R.id.menu_favorites -> {
                    viewPager.currentItem = 2
                    true
                }
                else -> false
            }
        }
    }
}