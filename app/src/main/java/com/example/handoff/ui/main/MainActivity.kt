package com.example.handoff.ui.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.*
import android.widget.TextView
import com.example.handoff.R
import com.example.handoff.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        viewPager.adapter = mSectionsPagerAdapter
        viewPager.offscreenPageLimit = 3

        tabs.setupWithViewPager(viewPager)
        setIconsFor(tabs)
    }

    private fun setIconsFor(tabLayout: TabLayout) {
        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_map_white_24dp)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_people_white_24dp)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_shopping_cart_white_24dp)
        tabLayout.getTabAt(3)!!.setIcon(R.drawable.ic_view_list_white_24dp)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            return true
        } else if (id == R.id.action_logout) {
            logout()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        getPrefs().edit().clear().apply()
        login()
    }

    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater!!.inflate(R.layout.fragment_main, container, false)
            val textView = rootView.findViewById(R.id.section_label) as TextView
            textView.text = getString(R.string.section_format, arguments.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        companion object {
            private val ARG_SECTION_NUMBER = "section_number"

            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return MapFragment.newInstance()
                else -> return PlaceholderFragment.newInstance(position + 1)
            }
        }

        override fun getCount(): Int {
            return 4
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "Home"
                1 -> return "Forum"
                2 -> return "Sale"
                3 -> return "Progress"
            }
            return null
        }
    }
}