class CreateComplaints < ActiveRecord::Migration[5.0]
  def change
    create_table :complaints do |t|
      t.string :subject
      t.text :description
      t.text :image
      t.decimal :latitude
      t.decimal :longitude
      t.string :city
      t.string :state
      t.integer :pincode

      t.timestamps
    end
  end
end
