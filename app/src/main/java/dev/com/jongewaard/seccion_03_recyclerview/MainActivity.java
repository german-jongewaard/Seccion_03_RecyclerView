package dev.com.jongewaard.seccion_03_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //declarando mi recycler view, lo primero la lista de nombres con la que voy a trabajar
    private List<Movie> movies;

    private RecyclerView mRecyclerView;

    //Puede ser declarado como 'RecyclerView.Adapter' o como nuestra clase adaptador 'MyAdapter'
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //aqui INSTANCIO la lista
        movies = this.getAllMovies();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        //Ejemplos!
        //mLayoutManager = new GridLayoutManager(this, 2);
        //mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        // Implementamos nuestro OnItemClickListener propio, sobreescribiendo el método que nosotros
        // definimos en el adaptador, y recibiendo los parámetros que necesitamos
        mAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {
                //Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_LONG).show();
              //  deleteMovie(position);
            }
        });

        // Lo usamos en caso de que sepamos que el layout no va a cambiar de tamaño, mejorando la performance
        mRecyclerView.setHasFixedSize(true);
        // Añade un efecto por defecto, si le pasamos null lo desactivamos por completo
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // Enlazamos el layout manager y adaptor directamente al recycler view
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
              //  this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Movie> getAllMovies(){
        return new ArrayList<Movie>(){{
            add(new Movie("Ben Hur", R.drawable.benhur));
            add(new Movie("DeadPool", R.drawable.deadpool));
            add(new Movie("Guardians of Galaxy", R.drawable.guardians));
            add(new Movie("Warcraft", R.drawable.warcraft));

        }};
    }

   /* private void addMovie(int position) {

        movies.add(position, "New movie " + (++counter));
        // Notificamos de un nuevo item insertado en nuestra colección
        mAdapter.notifyItemInserted(position);
        // Hacemos scroll hacia lo posición donde el nuevo elemento se aloja
        mLayoutManager.scrollToPosition(position);
    }

    private void deleteMovie(int position){

        movies.remove(position);
        // Notificamos de un item borrado en nuestra colección
        mAdapter.notifyItemRemoved(position);
    }*/
}
