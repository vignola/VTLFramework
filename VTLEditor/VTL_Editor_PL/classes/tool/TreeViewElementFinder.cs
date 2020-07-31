using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace VTL_Editor_PL.classes.tool
{   
        public class TreeViewElementFinder
        {
            private static string _textTobeSearched;
            private static TreeView _treeViewObject;
            private static TreeNode _founded;

            public delegate string StringInTheTree(string textToBeFiltered);

            #region Search a TreeNode into a TreeView

            public static TreeNode Search(TreeView TreeViewObject, string TextToBeSearched)
            {
                try
                {
                    _textTobeSearched = TextToBeSearched;
                    _treeViewObject = TreeViewObject;
                    _founded = null;
                    CallRecursive(_treeViewObject);
                    return _founded;
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.Search] " + ex.Message);
                }
            }

            private static void ScanRecursive(TreeNode treeNode)
            {
                try
                {
                    if (treeNode.Text.Contains(_textTobeSearched))
                    {
                        _founded = treeNode;
                    }

                    foreach (TreeNode tn in treeNode.Nodes)
                    {
                        if (_founded != null) break;
                        ScanRecursive(tn);
                    }
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.ScanRecursive] " + ex.Message);
                }
            }

            private static void CallRecursive(TreeView treeView)
            {
                try
                {
                    TreeNodeCollection nodes = treeView.Nodes;
                    foreach (TreeNode n in nodes)
                    {
                        if (_founded != null) break;
                        ScanRecursive(n);
                    }
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.CallRecursive] " + ex.Message);
                }
            }

            #endregion

            #region Search a TreeNode into a TreeView with filter applied to the node

            public static TreeNode Search(TreeView TreeViewObject, string TextToBeSearched, StringInTheTree Filter)
            {
                try
                {
                    _textTobeSearched = TextToBeSearched;
                    _treeViewObject = TreeViewObject;
                    _founded = null;
                    CallRecursiveFiltered(_treeViewObject, Filter);
                    return _founded;
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.Search] " + ex.Message);
                }
            }

            private static void CallRecursiveFiltered(TreeView treeView, StringInTheTree Filter)
            {
                try
                {
                    TreeNodeCollection nodes = treeView.Nodes;
                    foreach (TreeNode n in nodes)
                    {
                        if (_founded != null) break;
                        ScanRecursiveFiltered(n, Filter);
                    }
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.CallRecursiveFiltered] " + ex.Message);
                }
            }

            private static void ScanRecursiveFiltered(TreeNode treeNode, StringInTheTree Filter)
            {
                try
                {
                    if (Filter(treeNode.Text).Contains(_textTobeSearched))
                    {
                        _founded = treeNode;
                    }

                    foreach (TreeNode tn in treeNode.Nodes)
                    {
                        if (_founded != null) break;
                        ScanRecursiveFiltered(tn, Filter);
                    }
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.ScanRecursiveFiltered] " + ex.Message);
                }
            }

            #endregion

            #region Return the list of all the nodes

            public static List<TreeNode> GetNodes(TreeView treeView)
            {
                try
                {
                    List<TreeNode> checkedList = new List<TreeNode>();

                    foreach (TreeNode n in treeView.Nodes)
                    {
                        GetNodesRecursive(n, ref checkedList);
                    }

                    return checkedList;
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.GetNodes] " + ex.Message);
                }
            }

            private static void GetNodesRecursive(TreeNode treeNode, ref List<TreeNode> list)
            {
                try
                {
                    list.Add(treeNode);
                    foreach (TreeNode tn in treeNode.Nodes)
                    {
                        GetNodesRecursive(tn, ref list);
                    }
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.GetNodesRecursive] " + ex.Message);
                }
            }

            #endregion

            #region Return the list of the checked nodes

            public static List<TreeNode> GetCheckedNodes(TreeView treeView)
            {
                try
                {
                    List<TreeNode> checkedList = new List<TreeNode>();

                    foreach (TreeNode n in treeView.Nodes)
                    {
                        GetCheckedNodesRecursive(n, ref checkedList);
                    }

                    return checkedList;
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.GetCheckedNodes] " + ex.Message);
                }
            }

            private static void GetCheckedNodesRecursive(TreeNode treeNode, ref List<TreeNode> list)
            {
                try
                {
                    if (treeNode.Checked == true)
                    {
                        list.Add(treeNode);
                    }

                    foreach (TreeNode tn in treeNode.Nodes)
                    {
                        GetCheckedNodesRecursive(tn, ref list);
                    }
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.GetChechedNodesRecursive] " + ex.Message);
                }
            }

            #endregion

            #region Check/Uncheck all nodes in a TreeView

            private static void CheckAllRecursive(TreeNode treeNode, bool value)
            {
                try
                {
                    treeNode.Checked = value;
                    foreach (TreeNode tn in treeNode.Nodes)
                    {
                        CheckAllRecursive(tn, value);
                    }
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.CheckAllRecursive] " + ex.Message);
                }
            }

            public static void CheckAllTreeNodes(TreeView treeView, bool CheckValue)
            {
                try
                {
                    TreeNodeCollection nodes = treeView.Nodes;
                    foreach (TreeNode n in nodes)
                    {
                        CheckAllRecursive(n, CheckValue);
                    }
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.CheckAllTreeNodes] " + ex.Message);
                }
            }

            #endregion

            #region Invert checked nodes in a TreeView

            private static void InvertCheckAllRecursive(TreeNode treeNode)
            {
                try
                {
                    treeNode.Checked = !treeNode.Checked;
                    foreach (TreeNode tn in treeNode.Nodes)
                    {
                        InvertCheckAllRecursive(tn);
                    }
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.InvertCheckAllRecursive] " + ex.Message);
                }
            }

            public static void InvertCheckAllTreeNodes(TreeView treeView)
            {
                try
                {
                    TreeNodeCollection nodes = treeView.Nodes;
                    foreach (TreeNode n in nodes)
                    {
                        InvertCheckAllRecursive(n);
                    }
                }
                catch (Exception ex)
                {
                    throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.InvertCheckAllTreeNodes] " + ex.Message);
                }
            }

            #endregion

            //#region Check in tree a list of node

            //public static TreeNode CheckNodes(ref TreeView TreeViewObject, string TextToBeSearched, StringInTheTree Filter)
            //{
            //    try
            //    {
            //        _textTobeSearched = TextToBeSearched;
            //        _treeViewObject = TreeViewObject;
            //        _founded = null;
            //        CallRecursiveFiltered(_treeViewObject, Filter);
            //        return _founded;
            //    }
            //    catch (Exception ex)
            //    {
            //        throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.Search] " + ex.Message);
            //    }
            //}

            //private static void CallRecursiveFiltered(TreeView treeView, StringInTheTree Filter)
            //{
            //    try
            //    {
            //        TreeNodeCollection nodes = treeView.Nodes;
            //        foreach (TreeNode n in nodes)
            //        {
            //            if (_founded != null) break;
            //            ScanRecursiveFiltered(n, Filter);
            //        }
            //    }
            //    catch (Exception ex)
            //    {
            //        throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.CallRecursiveFiltered] " + ex.Message);
            //    }
            //}

            //private static void ScanRecursiveFiltered(TreeNode treeNode, StringInTheTree Filter)
            //{
            //    try
            //    {
            //        if (Filter(treeNode.Text).Contains(_textTobeSearched))
            //        {
            //            _founded = treeNode;
            //        }

            //        foreach (TreeNode tn in treeNode.Nodes)
            //        {
            //            if (_founded != null) break;
            //            ScanRecursiveFiltered(tn, Filter);
            //        }
            //    }
            //    catch (Exception ex)
            //    {
            //        throw new Exception("Error, [GetDataDLL.Tools.TreeTools.TreeViewElementFinder.ScanRecursiveFiltered] " + ex.Message);
            //    }
            //}

            //#endregion

        }
    }

