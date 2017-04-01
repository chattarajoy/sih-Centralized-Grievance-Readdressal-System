class CreateSlas < ActiveRecord::Migration[5.0]
  def change
    create_table :slas do |t|
      t.string :category
      t.integer :time

      t.timestamps
    end
  end
end
