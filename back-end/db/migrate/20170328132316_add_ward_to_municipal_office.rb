class AddWardToMunicipalOffice < ActiveRecord::Migration[5.0]
  def change
    add_column :municipal_offices, :ward, :string
  end
end
