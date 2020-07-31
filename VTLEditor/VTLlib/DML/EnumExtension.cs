using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Reflection;
namespace VTLlib
{
    public class EnumExtension 
    {
        [AttributeUsage(AttributeTargets.Field)]
        public class Key : Attribute
        {



            private string m_name;
            public Key(string name)
            {
                this.m_name = name;
            }
            public static string Get(Type tp, string name)
            {
                MemberInfo[] mi = tp.GetMember(name);
                if (mi != null && mi.Length > 0)
                {
                    Key attr = Attribute.GetCustomAttribute(mi[0],
                        typeof(Key)) as Key;
                    if (attr != null)
                    {
                        return attr.m_name;
                    }
                }
                return null;
            }
            public static string GetbyDescription(Type tp,String enumDescription)
            {
                string enumValue = null;
                for (int i = 0; i <  tp.GetEnumNames().Length; i++)
                {

                    System.Reflection.FieldInfo oFieldInfo = tp.GetField(tp.GetEnumNames()[i]);
                    DescriptionAttribute[] attributes = (DescriptionAttribute[])oFieldInfo.GetCustomAttributes(typeof(DescriptionAttribute), false);
                    if (attributes[0].Description == enumDescription)
                    {
                        enumValue = tp.GetEnumNames()[i];
                    }
                }
                MemberInfo[] mi = tp.GetMember(enumValue);
                if (mi != null && mi.Length > 0)
                {
                    Key attr = Attribute.GetCustomAttribute(mi[0],
                        typeof(Key)) as Key;
                    if (attr != null)
                    {
                        return attr.m_name;
                    }
                }
                return null;
            }
            //public static string Get(object enm)
            //{
            //    if (enm != null)
            //    {
            //        MemberInfo[] mi = enm.GetType().GetMember(enm.ToString());
            //        if (mi != null && mi.Length > 0)
            //        {
            //            Key attr = Attribute.GetCustomAttribute(mi[0],
            //                typeof(Key)) as Key;
            //            if (attr != null)
            //            {
            //                return attr.m_name;
            //            }
            //        }
            //    }
            //    return null;
            //}
            //public static string Get(Type tp, string description)
            //{
            //    if (enm != null)
            //    {
            //        MemberInfo[] mi = enm.GetType().GetMember(enm.ToString());
            //        if (mi != null && mi.Length > 0)
            //        {
            //            Key attr = Attribute.GetCustomAttribute(mi[0],
            //                typeof(Key)) as Key;
            //            if (attr != null)
            //            {
            //                return attr.m_name;
            //            }
            //        }
            //    }
            //    return null;
            //}
        }
        [AttributeUsage(AttributeTargets.Field)]
        public class Group : Attribute
        {

            private string m_name;
            public Group(string name)
            {
                this.m_name = name;
            }
            public static string Get(Type tp, string name)
            {
                MemberInfo[] mi = tp.GetMember(name);
                if (mi != null && mi.Length > 0)
                {
                    Group attr = Attribute.GetCustomAttribute(mi[0],
                        typeof(Group)) as Group;
                    if (attr != null)
                    {
                        return attr.m_name;
                    }
                }
                return null;
            }
            public static string Get(object enm)
            {
                if (enm != null)
                {
                    MemberInfo[] mi = enm.GetType().GetMember(enm.ToString());
                    if (mi != null && mi.Length > 0)
                    {
                        Group attr = Attribute.GetCustomAttribute(mi[0],
                            typeof(Group)) as Group;
                        if (attr != null)
                        {
                            return attr.m_name;
                        }
                    }
                }
                return null;
            }

 
        }
        public List<String> getDescriptions(Type EnumType, String enumGroup)
        {
            List<String> enumDescriptions = new List<String>();
            for (int i = 0; i < EnumType.GetEnumNames().Length; i++)
            {
                System.Reflection.FieldInfo oFieldInfo = EnumType.GetField(EnumType.GetEnumNames()[i]);
                DescriptionAttribute[] attributes = (DescriptionAttribute[])oFieldInfo.GetCustomAttributes(typeof(DescriptionAttribute), false);
                if (attributes.Length > 0)
                {
                    if (Group.Get(EnumType, EnumType.GetEnumNames()[i]) == enumGroup) enumDescriptions.Add(attributes[0].Description);
                }
            }
            return enumDescriptions;
        }

    }
}




