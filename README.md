# ItemClickRecyclerView
Listen item click event in RecyclerView in a more elegant way. Use only one Listener rather than one OnClickListener for each item.

Listen item click event in RecyclerView just like we do in ListView:
```
BaseRecyclerView recyclerView = (BaseRecyclerView) findViewById(R.id.rv);
recyclerView.setOnItemClickListener(new BaseRecyclerView.OnItemClickListener() {
     @Override
     public void onItemClick(View item, int adapterPosition, RecyclerView.Adapter adapter) {
           Toast.makeText(this, "postion "+adapterPosition+" is clicked", Toast.LENGTH_SHORT).show();
     }
});
```
