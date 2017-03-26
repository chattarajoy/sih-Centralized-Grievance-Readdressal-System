class CreateAadhars < ActiveRecord::Migration[5.0]
  def change
    create_table :aadhars do |t|
      t.string :uid
      t.string :name
      t.string :phone

      t.timestamps
    end
  end
end
