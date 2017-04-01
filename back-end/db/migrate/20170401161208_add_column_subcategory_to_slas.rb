class AddColumnSubcategoryToSlas < ActiveRecord::Migration[5.0]
  def change
  	add_column :slas,:subcategory , :string
  end
end
