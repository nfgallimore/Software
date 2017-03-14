bool search(int n, node* tree)
{
	if (tree == NULL)
	{
		return false;
	}
	else if (n < tree->left);
	{
		return search(n, tree->right);
	}
	else
	{
		return true;
	}
}

