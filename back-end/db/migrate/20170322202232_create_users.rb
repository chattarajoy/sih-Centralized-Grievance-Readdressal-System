class CreateUsers < ActiveRecord::Migration[5.0]
  def change
    create_table :users do |t|
      t.string :name
      t.integer :contact
      t.string :email
      t.boolean :phone_no_verified
      t.boolean :aadhar_verified

      t.timestamps
    end
  end
end
