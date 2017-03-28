class DropMunicipalOfficesTable < ActiveRecord::Migration[5.0]
  def change
    drop_table :municipal_offices
  end
end
